package org.tinker.big.data.unstructured.elasticsearch.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.xcontent.XContentType;
import org.tinker.big.data.unstructured.elasticsearch.model.Student;
import org.tinker.big.data.unstructured.elasticsearch.util.ESWrapper;

import java.util.HashMap;
import java.util.Map;

public class StudentIndexer {

    private static final String INDEX = "student"; //index must start with lower case!
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final ESWrapper esWrapper;

    public StudentIndexer(ESWrapper esWrapper) {
        this.esWrapper = esWrapper;
    }

    private Student insertById(Student student) {

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("studentId", student.getStudentId());
        dataMap.put("studentName", student.getStudentName());
        IndexRequest indexRequest = new IndexRequest(INDEX)
                .source(dataMap);
        try {
            IndexResponse response = esWrapper.index(indexRequest);
            System.out.println(response);
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        return student;
    }

    private Student getById(String studentId) {
        GetRequest getPersonRequest = new GetRequest(INDEX, studentId);
        try {
            GetResponse getResponse = esWrapper.get(getPersonRequest);
            return getResponse != null
                    ? objectMapper.convertValue(getResponse.getSourceAsMap(), Student.class) : null;
        }
        catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        return null;
    }

    private Student updateById(String studentId, Student student) {
        UpdateRequest updateRequest = new UpdateRequest(INDEX, studentId)
                .fetchSource(true);    // Fetch Object after its update
        try {
            String studentJSON = objectMapper.writeValueAsString(student);
            updateRequest.doc(studentJSON, XContentType.JSON);
            UpdateResponse updateResponse = esWrapper.update(updateRequest);
            return objectMapper.convertValue(updateResponse.getGetResult().sourceAsMap(), Student.class);
        } catch (JsonProcessingException e) {
            e.getMessage();
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        System.out.println("Unable to update student");
        return null;
    }

    private void deleteById(String studentId) {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX, studentId);
        try {
            DeleteResponse deleteResponse = esWrapper.delete(deleteRequest);
            System.out.println(deleteResponse);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
    }

    public static void main(String[] args) {
        ESWrapper esWrapper = ESWrapper.getInstance();
        try {
            StudentIndexer instance = new StudentIndexer(esWrapper);
            Student s1 = new Student("1", "Leon");
            Student s2 = new Student("2", "Shawn");

            //Insertion
            System.out.println(instance.insertById(s1));
            System.out.println(instance.insertById(s2));

            //Retrieve by id
            Student s3 = instance.getById("1");
            System.out.println("Retrieved: ==== " + s3);

            //Update
            if (s3 != null) {
                s3.setStudentName("Jack Sparrow");
                System.out.println(instance.updateById("1", s3));
            }

            //Deletion
            instance.deleteById("1");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //Close Connection
            esWrapper.closeConnection();
        }
    }
}
