package com.vetsmart.persistance.migrations;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;

import java.io.IOException;
import java.text.ParseException;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "initialLoad", author = "SYSTEM")
    public void loadDocuments(MongockTemplate mongockTemplate) throws IOException, ParseException {
        // ResourceLoader resourceLoader = new DefaultResourceLoader();
        // Resource resource = resourceLoader.getResource("classpath:migrations/data.json");
        // String json = new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);

        // // Documento JSON que queremos insertar
        // List<Document> documents = parseJsonToList(json);

        // // Insertamos el documento en la colección
        // mongockTemplate.getCollection("Dosimeters").insertMany(documents);
    }

    // public static List<Document> parseJsonToList(String json) throws ParseException {
        // List<Document> documentList = new ArrayList<>();
        // BsonArray bsonArray = BsonArray.parse(json);

        // for (BsonValue bsonValue : bsonArray) {
        //     BsonDocument bsonDocument = bsonValue.asDocument();
        //     List<Document> receptions = new ArrayList<>();
        //     List<Document> readings = new ArrayList<>();
        //     List<Document> shipments = new ArrayList<>();

        //     Document userDocument = new Document()
        //             .append("firstName", existPropertyInJson(bsonDocument.get("firstName")) ? bsonDocument.get("firstName") : null)
        //             .append("lastName", existPropertyInJson(bsonDocument.get("lastName")) ? bsonDocument.get("lastName") : null)
        //             .append("identification", existPropertyInJson(bsonDocument.get("identification")) ? bsonDocument.get("identification") : null)
        //             .append("company", existPropertyInJson(bsonDocument.get("company")) ? bsonDocument.get("company") : null)
        //             .append("birthday", existPropertyInJson(bsonDocument.get("birthday")) ? bsonDocument.get("birthday") : null);

        //     Document dosimeterDocument = new Document()
        //             .append("barcode", existPropertyInJson(bsonDocument.get("barcode")) ? bsonDocument.get("barcode") : null)
        //             .append("dosimeterNumber", existPropertyInJson(bsonDocument.get("dosimeterNumber")) ? bsonDocument.get("dosimeterNumber") : null)
        //             .append("department", existPropertyInJson(bsonDocument.get("department")) ? bsonDocument.get("department") : null)
        //             .append("assignmentDate", existPropertyInJson(bsonDocument.get("assignmentDate")) ? parseAssignmentDateValueToDate(bsonDocument) : null)
        //             .append("creationDate", new Date())
        //             .append("updateDate", new Date())
        //             .append("active", true)
        //             .append("user", userDocument)
        //             .append("receptions", receptions)
        //             .append("readings", readings)
        //             .append("shipments", shipments)                    ;

        //     documentList.add(dosimeterDocument);
        // }

        // return documentList;
    // }

    // private static Date parseAssignmentDateValueToDate(BsonDocument bsonDocument) throws ParseException {
    //     SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
    //     String value = extractStringValueForAssignmentDate(bsonDocument);
    //     return sdf.parse(value);
    // }

    // private static String extractStringValueForAssignmentDate(BsonDocument bsonDocument) {
    //     return bsonDocument.get("assignmentDate").toString().replace("BsonString{value='", "").replace("'}", "");
    // }

    // private static boolean existPropertyInJson(BsonValue bsonValue){
    //     return null != bsonValue;
    // }
}
