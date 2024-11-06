
package com.TripMap.pojo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import lombok.Data;

@Data
public class Userfavors {
    String uuid;
    List<String> id;
    public Userfavors(String uuid,List<String> id){
        this.uuid=uuid;
        this.id=id;
    }
    public Userfavors(Document doc){
        this.uuid=doc.getString("uuid");
        this.id=doc.getList("id",String.class);
    }
    public Userfavors(String uuid){
        this.uuid=uuid;
        this.id=new ArrayList<>();
    }
    public Document toDocument(){
        Document doc=new Document();
        doc.append("uuid", uuid);
        doc.append("id", id);
        return doc;
    }
    public Userfavors(){}
}
