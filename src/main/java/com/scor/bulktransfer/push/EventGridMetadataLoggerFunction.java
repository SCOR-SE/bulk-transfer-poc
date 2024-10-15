package com.scor.bulktransfer.push;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.scor.bulktransfer.push.models.EventSchema;
import com.scor.bulktransfer.push.services.JsonService;
import com.scor.bulktransfer.push.services.MetadataService;
import com.scor.bulktransfer.push.services.StorageService;

import java.util.Map;

/**
 * Azure Function triggered by Event Grid.
 */
public class EventGridMetadataLoggerFunction {

    private final MetadataService metadataService = new MetadataService();
    private final JsonService jsonService = new JsonService();
    private final StorageService storageService = new StorageService();

    @FunctionName("EventGridListener")
    public void run(
            @EventGridTrigger(name = "event") EventSchema event,
            final ExecutionContext context) {

        context.getLogger().info("EventGridListener function triggered.");

        try {
            Map<String, Object> metadata = metadataService.createMetadata(event);
            String metadataJson = jsonService.convertToJson(metadata);
            storageService.logDataToTableStorage(metadataJson, event.id, context);
        } catch (Exception e) {
            context.getLogger().severe("Error processing EventGrid event: " + e.getMessage());
            // todo rethrow or handle
        }
    }
}
