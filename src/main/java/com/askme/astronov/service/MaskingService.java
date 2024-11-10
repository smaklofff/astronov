package com.askme.astronov.service;

import com.askme.astronov.utils.ConverterUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MaskingService {


//    @Value("${mask.fields}")
//    private String[] strToMask;

//    public <T> T mask(T obj) {
//        recursiveMasking(ConverterUtil.convertObjectToObject(
//                obj, new TypeReference<>() {}));
//        return ConverterUtil.convertObjectToObject(obj, new TypeReference<>() {});
//    }

//    private void recursiveMasking(JsonNode obj) {
//        if (obj.isObject()) {
//            if (obj.isTextual() && Arrays.asList(strToMask).contains(obj.asText())) {
//                ObjectNode objectNode = (ObjectNode) obj;
//                objectNode.put(obj.fieldNames().next(), mask(objectNode.get(obj.fieldNames().next())));
//            }
//        } else if (obj.isArray()) {
//            obj.forEach(this::recursiveMasking);
//        }
//    }
}
