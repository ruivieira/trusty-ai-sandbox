package com.redhat.developer.model;

import java.net.URI;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Factory class for {@link Feature}s
 */
public class FeatureFactory {

    private FeatureFactory() {
    }

    public static Feature newTextFeature(String name, String text) {
        return new Feature(name, Type.TEXT, new Value<>(text));
    }

    public static Feature newCategoricalFeature(String name, String category) {
        return new Feature(name, Type.CATEGORICAL, new Value<>(category));
    }

    public static Feature newNumericalFeature(String name, Number number) {
        return new Feature(name, Type.NUMBER, new Value<>(number));
    }

    public static Feature newBooleanFeature(String name, Boolean truthValue) {
        return new Feature(name, Type.BOOLEAN, new Value<>(truthValue));
    }

    public static Feature newDateFeature(String name, Date date) {
        return new Feature(name, Type.DATE, new Value<>(date));
    }

    public static Feature newCurrencyFeature(String name, Currency currency) {
        return new Feature(name, Type.CURRENCY, new Value<>(currency));
    }

    public static Feature newBinaryFeature(String name, ByteBuffer byteBuffer) {
        return new Feature(name, Type.BINARY, new Value<>(byteBuffer));
    }

    public static Feature newURIFeature(String name, URI uri) {
        return new Feature(name, Type.URI, new Value<>(uri));
    }

    public static Feature newDurationFeature(String name, Duration duration) {
        return new Feature(name, Type.DURATION, new Value<>(duration));
    }

    public static Feature newTimeFeature(String name, LocalTime time) {
        return new Feature(name, Type.TIME, new Value<>(time));
    }

    public static Feature newVectorFeature(String name, double... doubles) {
        return new Feature(name, Type.VECTOR, new Value<>(doubles));
    }

    public static Feature newObjectFeature(String name, Object object) {
        return new Feature(name, Type.NESTED, new Value<>(object));
    }

    public static Feature newCompositeFeature(String name, Map<String, Object> map) {
        List<Feature> features = new LinkedList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            String featureName = entry.getKey();
            Feature feature;
            if (value instanceof Map) {
                feature = newCompositeFeature(featureName, (Map<String, Object>) value);
            } else if (value instanceof double[]) {
                feature = newVectorFeature(featureName, (double[]) value);
            } else if (value instanceof LocalTime) {
                feature = newTimeFeature(featureName, (LocalTime) value);
            } else if (value instanceof Duration) {
                feature = newDurationFeature(featureName, (Duration) value);
            } else if (value instanceof URI) {
                feature = newURIFeature(featureName, (URI) value);
            } else if (value instanceof ByteBuffer) {
                feature = newBinaryFeature(featureName, (ByteBuffer) value);
            } else if (value instanceof Currency) {
                feature = newCurrencyFeature(featureName, (Currency) value);
            } else if (value instanceof Date) {
                feature = newDateFeature(featureName, (Date) value);
            } else if (value instanceof Boolean) {
                feature = newBooleanFeature(featureName, (Boolean) value);
            } else if (value instanceof Number) {
                feature = newNumericalFeature(featureName, (Number) value);
            } else if (value instanceof String) {
                feature = newTextFeature(featureName, (String) value);
            } else if (value instanceof Feature) {
                feature = (Feature) value;
            } else {
                feature = newObjectFeature(featureName, value);
            }
            features.add(feature);
        }
        return new Feature(name, Type.COMPOSITE, new Value<>(features));
    }
}
