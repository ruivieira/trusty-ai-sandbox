package com.redhat.developer.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.redhat.developer.database.mongo.MongoQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MongoStorageManager implements IStorageManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoStorageManager.class);

    private MongoDatabase database;

    @Inject
    MongoClient defaultMongoClient;

    @PostConstruct
    void setup() {
        database = defaultMongoClient.getDatabase("myMongoDb");
    }

    @Override
    public <T> boolean create(String key, T request, String index) {
        MongoCollection<T> collection = getOrCreateCollection(index, request.getClass());

        collection.insertOne(request);

        return true;
    }

    @Override
    public <T> List<T> search(TrustyStorageQuery query, String index, Class<T> type) {
        MongoCollection<T> collection = getOrCreateCollection(index, type);

        Iterator<T> i = collection.find(MongoQueryFactory.build(query, index)).iterator();
        List<T> copy = new ArrayList<T>();
        while (i.hasNext()) {
            copy.add(i.next());
        }

        return copy;
    }

    @Override
    public boolean deleteIndex(String index) {
        MongoCollection collection = database.getCollection(index);
        collection.drop();
        return true;
    }

    private MongoCollection getOrCreateCollection(String collection, Class type) {
        return database.getCollection(collection, type);
    }
}
