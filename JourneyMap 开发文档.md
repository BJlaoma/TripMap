# JourneyMap 开发文档

## 目录
1. [项目概述](#项目概述)
2. [技术栈与工具](#技术栈与工具)
3. [数据库设计](#数据库设计)
4. [后端开发](#后端开发)
    - [环境设置](#环境设置)
    - [模型定义](#模型定义)
    - [API 接口](#api-接口)
    - [图片上传](#图片上传)
    - [安全与认证](#安全与认证)
5. [前端开发](#前端开发)
    - [环境设置](#环境设置-1)
    - [页面设计](#页面设计)
    - [API 集成](#api-集成)
6. [部署与运维](#部署与运维)
7. [优化与扩展](#优化与扩展)

---

## 项目概述
**JourneyMap** 是一个微信小程序，提供互动旅游地图，标注景点和餐厅，支持搜索、筛选、收藏和导航功能。

## 技术栈与工具
- **前端**：微信小程序框架（WXML, WXSS, JavaScript）
- **后端**：Java Spring Boot, Spring Data MongoDB
- **数据库**：MongoDB Atlas 或 腾讯云 MongoDB
- **云存储**：腾讯云 COS
- **开发工具**：IntelliJ IDEA, 微信开发者工具
- **版本控制**：Git, GitHub

## 数据库设计

### 集合结构
1. **Users**
    ```json
    {
      "_id": ObjectId,
      "openid": "string",
      "nickname": "string",
      "avatar_url": "string",
      "favorites": {
        "scenic": [ObjectId],
        "restaurant": [ObjectId]
      },
      "created_at": ISODate,
      "updated_at": ISODate
    }
    ```

2. **ScenicSpots**
    ```json
    {
      "_id": ObjectId,
      "name": "string",
      "description": "string",
      "location": {
        "type": "Point",
        "coordinates": [longitude, latitude]
      },
      "phone": "string",
      "open_time": "string",
      "category": "string",
      "links": ["string"],
      "images": ["string"],
      "created_at": ISODate,
      "updated_at": ISODate
    }
    ```

3. **Restaurants**
    ```json
    {
      "_id": ObjectId,
      "name": "string",
      "description": "string",
      "location": {
        "type": "Point",
        "coordinates": [longitude, latitude]
      },
      "phone": "string",
      "open_time": "string",
      "category": "string",
      "links": ["string"],
      "images": ["string"],
      "created_at": ISODate,
      "updated_at": ISODate
    }
    ```

### 索引设计
- **ScenicSpots & Restaurants**
  - `category`：单字段索引
  - `location`：2dsphere 地理索引
  - `name`：文本索引（可选）

- **Users**
  - `openid`：唯一索引
  - `favorites.scenic` 和 `favorites.restaurant`：数组索引

## 后端开发

### 环境设置
1. **安装 Java JDK 11+**。
2. **安装 Maven**。
3. **创建 Spring Boot 项目**：
   - 使用 [Spring Initializr](https://start.spring.io/) 选择依赖：
     - Spring Web
     - Spring Data MongoDB
     - Lombok (可选)
     - Spring Boot DevTools (可选)
4. **配置 MongoDB 连接** (`application.properties`):
    ```properties
    spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.mongodb.net/journeymap?retryWrites=true&w=majority
    spring.data.mongodb.database=journeymap
    ```

### 模型定义
#### 用户模型
```java
@Data
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private String openid;
    private String nickname;
    private String avatarUrl;
    private Favorites favorites;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

@Data
public class Favorites {
    private List<String> scenic;
    private List<String> restaurant;
}
```

#### 景点模型
```java
@Data
@Document(collection = "ScenicSpots")
public class ScenicSpot {
    @Id
    private String id;
    private String name;
    private String description;
    private Point location;
    private String phone;
    private String openTime;
    private String category;
    private List<String> links;
    private List<String> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```


#### 餐厅模型
```java
@Data
@Document(collection = "Restaurants")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String description;
    private Point location;
    private String phone;
    private String openTime;
    private String category;
    private List<String> links;
    private List<String> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

### API 接口
#### 用户控制器
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestBody UserLoginRequest request) {
        Optional<User> optionalUser = userRepository.findByOpenid(request.getOpenid());
        User user = optionalUser.orElseGet(User::new);
        user.setOpenid(request.getOpenid());
        user.setNickname(request.getNickname());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setUpdatedAt(LocalDateTime.now());
        if (optionalUser.isEmpty()) {
            user.setCreatedAt(LocalDateTime.now());
            user.setFavorites(new Favorites(new ArrayList<>(), new ArrayList<>()));
        }
        return userRepository.save(user);
    }

    @PostMapping("/{userId}/favorites")
    public ResponseEntity<?> addFavorite(@PathVariable String userId, @RequestBody FavoriteRequest request) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        User user = userOpt.get();
        if (request.getObjectType().equals("scenic")) {
            user.getFavorites().getScenic().add(request.getObjectId());
        } else if (request.getObjectType().equals("restaurant")) {
            user.getFavorites().getRestaurant().add(request.getObjectId());
        }
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.ok("Favorite added");
    }

    @DeleteMapping("/{userId}/favorites")
    public ResponseEntity<?> removeFavorite(@PathVariable String userId, @RequestBody FavoriteRequest request) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        User user = userOpt.get();
        if (request.getObjectType().equals("scenic")) {
            user.getFavorites().getScenic().remove(request.getObjectId());
        } else if (request.getObjectType().equals("restaurant")) {
            user.getFavorites().getRestaurant().remove(request.getObjectId());
        }
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.ok("Favorite removed");
    }
}

@Data
public class UserLoginRequest {
    private String openid;
    private String nickname;
    private String avatarUrl;
}

@Data
public class FavoriteRequest {
    private String objectType; // "scenic" or "restaurant"
    private String objectId;
}
```

#### 景点控制器
```java
@RestController
@RequestMapping("/api/scenic-spots")
public class ScenicSpotController {

    @Autowired
    private ScenicSpotRepository scenicSpotRepository;

    @GetMapping
    public List<ScenicSpot> getAllScenicSpots() {
        return scenicSpotRepository.findAll();
    }

    @GetMapping("/search")
    public List<ScenicSpot> searchScenicSpots(@RequestParam String name) {
        return scenicSpotRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/filter")
    public List<ScenicSpot> filterScenicSpots(@RequestParam String category) {
        return scenicSpotRepository.findByCategory(category);
    }
}
```

#### 餐厅控制器
```java
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/search")
    public List<Restaurant> searchRestaurants(@RequestParam String name) {
        return restaurantRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/filter")
    public List<Restaurant> filterRestaurants(@RequestParam String category) {
        return restaurantRepository.findByCategory(category);
    }
}
```

### 图片上传
**前端直接上传至腾讯云 COS**：
1. **配置 COS Bucket**。
2. **获取上传签名**：
    - 后端提供一个获取上传签名的接口。
    - 示例：
    ```java
    @RestController
    @RequestMapping("/api/upload")
    public class UploadController {
    
        @Autowired
        private CosService cosService;
    
        @GetMapping("/signature")
        public UploadSignature getUploadSignature() {
            return cosService.generateSignature();
        }
    }
    
    @Data
    public class UploadSignature {
        private String signature;
        private String policy;
        // 其他必要字段
    }
    ```
3. **前端上传**：
    - 获取签名后，使用 `wx.uploadFile` 直接上传到 COS。
    - 获取返回的图片 URL，存储在相应集合中。

### 安全与认证
1. **认证**：
    - 基于微信的 `openid` 进行用户认证。
    - 使用 JWT 生成和验证用户 token（可选）。
2. **权限控制**：
    - 普通用户只能操作自己的收藏。
    - 管理员拥有额外权限（如管理景点和餐厅）。
3. **数据验证**：
    - 后端校验所有输入数据，防止注入攻击。

## 前端开发

### 环境设置
1. **安装微信开发者工具**。
2. **创建新小程序项目**，选择空白模板。

### 页面设计
1. **首页（地图页）**：
    - 地图组件展示景点和餐厅。
    - 搜索栏和筛选按钮。
2. **详情页**：
    - 展示景点或餐厅详情。
    - 收藏按钮和导航按钮。
3. **用户页**：
    - 展示用户信息和收藏列表。

### API 集成
1. **用户登录**：
    - 调用后端 `/api/users/login` 接口。
2. **获取景点和餐厅**：
    - 调用 `/api/scenic-spots` 和 `/api/restaurants` 接口。
3. **搜索与筛选**：
    - 调用相应的搜索和筛选 API。
4. **收藏操作**：
    - 调用 `/api/users/{userId}/favorites` 添加或移除收藏。

### 示例代码
#### 首页地图
```xml
<!-- pages/index/index.wxml -->
<view class="container">
  <map
    longitude="{{longitude}}"
    latitude="{{latitude}}"
    scale="14"
    markers="{{markers}}"
    bindmarkertap="onMarkerTap"
    style="width: 100%; height: 100vh;"
  ></map>
  <view class="search-bar">
    <input placeholder="搜索景点或餐厅" bindinput="onSearchInput" />
    <button bindtap="onSearch">搜索</button>
  </view>
</view>
```

#### 首页逻辑
```javascript
// pages/index/index.js
Page({
  data: {
    latitude: 39.916527,
    longitude: 116.397128,
    markers: [],
    searchQuery: '',
    userId: '',
  },
  onLoad: function() {
    this.login();
    this.getLocation();
  },
  login: function() {
    wx.login({
      success: res => {
        wx.getUserInfo({
          success: userInfoRes => {
            wx.request({
              url: 'https://your-backend.com/api/users/login',
              method: 'POST',
              data: {
                openid: res.code, // 替换为微信获取的 openid
                nickname: userInfoRes.userInfo.nickName,
                avatarUrl: userInfoRes.userInfo.avatarUrl,
              },
              success: userRes => {
                this.setData({ userId: userRes.data.id });
                this.fetchScenicSpots();
                this.fetchRestaurants();
              }
            });
          }
        });
      }
    });
  },
  getLocation: function() {
    wx.getLocation({
      type: 'gcj02',
      success: res => {
        this.setData({
          latitude: res.latitude,
          longitude: res.longitude,
        });
      }
    });
  },
  fetchScenicSpots: function() {
    wx.request({
      url: 'https://your-backend.com/api/scenic-spots',
      method: 'GET',
      success: res => {
        const scenicMarkers = res.data.map(spot => ({
          id: spot._id,
          latitude: spot.location.coordinates[1],
          longitude: spot.location.coordinates[0],
          title: spot.name,
          iconPath: "/resources/scenic.png",
          width: 30,
          height: 30,
        }));
        this.setData({ markers: [...this.data.markers, ...scenicMarkers] });
      }
    });
  },
  fetchRestaurants: function() {
    wx.request({
      url: 'https://your-backend.com/api/restaurants',
      method: 'GET',
      success: res => {
        const restaurantMarkers = res.data.map(rest => ({
          id: rest._id,
          latitude: rest.location.coordinates[1],
          longitude: rest.location.coordinates[0],
          title: rest.name,
          iconPath: "/resources/restaurant.png",
          width: 30,
          height: 30,
        }));
        this.setData({ markers: [...this.data.markers, ...restaurantMarkers] });
      }
    });
  },
  onMarkerTap: function(e) {
    const markerId = e.markerId;
    // 跳转到详情页
    wx.navigateTo({
      url: `/pages/detail/detail?id=${markerId}`
    });
  },
  onSearchInput: function(e) {
    this.setData({ searchQuery: e.detail.value });
  },
  onSearch: function() {
    const query = this.data.searchQuery.trim();
    if (!query) {
      this.fetchScenicSpots();
      this.fetchRestaurants();
      return;
    }
    // 搜索景点
    wx.request({
      url: `https://your-backend.com/api/scenic-spots/search?name=${query}`,
      method: 'GET',
      success: res => {
        const scenicMarkers = res.data.map(spot => ({
          id: spot._id,
          latitude: spot.location.coordinates[1],
          longitude: spot.location.coordinates[0],
          title: spot.name,
          iconPath: "/resources/scenic.png",
          width: 30,
          height: 30,
        }));
        // 搜索餐厅
        wx.request({
          url: `https://your-backend.com/api/restaurants/search?name=${query}`,
          method: 'GET',
          success: restRes => {
            const restaurantMarkers = restRes.data.map(rest => ({
              id: rest._id,
              latitude: rest.location.coordinates[1],
              longitude: rest.location.coordinates[0],
              title: rest.name,
              iconPath: "/resources/restaurant.png",
              width: 30,
              height: 30,
            }));
            this.setData({ markers: [...scenicMarkers, ...restaurantMarkers] });
          }
        });
      }
    });
  }
});
```

## 部署与运维

1. **后端部署**：
    - 部署 Spring Boot 应用至云平台（如腾讯云 CVM, AWS EC2）。
    - 配置环境变量，确保安全连接到 MongoDB 和 COS。

2. **数据库托管**：
    - 使用 MongoDB Atlas 或 腾讯云 MongoDB 托管服务。
    - 设置备份策略和访问权限。

3. **云存储配置**：
    - 配置腾讯云 COS Bucket 权限，确保安全上传和访问图片。

4. **域名与 SSL**：
    - 申请并配置域名。
    - 配置 HTTPS 证书，确保 API 通信加密。

## 优化与扩展

1. **性能优化**：
    - 添加必要的索引，优化查询性能。
    - 实现分页查询，避免一次性加载大量数据。

2. **缓存机制**：
    - 使用 Redis 缓存热点数据，提高读取速度。

3. **错误处理**：
    - 实现全局异常处理，返回统一的错误响应格式。

4. **日志管理**：
    - 集成日志框架（如 Logback），记录关键操作和错误日志。

5. **扩展功能**（未来）：
    - 添加推荐表、心愿排行榜、点赞功能，扩展数据库结构。
    - 实现用户评论和评分系统。

## 总结
本开发文档涵盖了 **JourneyMap** 微信小程序的核心开发步骤，包括技术栈选择、数据库设计、后端与前端开发、部署与运维以及优化与扩展建议。通过简化数据库结构为用户、景点和餐厅三个集合，并合理设计索引和API接口，确保系统的高效性和可扩展性。

如有进一步问题，参考相关技术文档或联系团队成员协作解决。

祝开发顺利，取得优异成绩！