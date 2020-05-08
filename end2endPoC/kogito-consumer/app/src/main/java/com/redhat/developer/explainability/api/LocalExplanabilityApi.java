package com.redhat.developer.explainability.api;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.developer.execution.models.OutcomeModelWithInputs;
import com.redhat.developer.explainability.IExplainabilityService;
import com.redhat.developer.explainability.model.LimeResponse;
import com.redhat.developer.explainability.model.Saliency;
import com.redhat.developer.explainability.responses.local.DecisionExplanationResponse;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/executions/decisions/")
public class LocalExplanabilityApi {

    @Inject
    IExplainabilityService explainabilityService;

    @GET
    @Path("{key}/featureImportance")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @APIResponses(value = {
            @APIResponse(description = "Gets the local explanation of a decision.", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = DecisionExplanationResponse.class))),
            @APIResponse(description = "Bad Request", responseCode = "400", content = @Content(mediaType = MediaType.TEXT_PLAIN))
    }
    )
    @Operation(summary = "Returns the feature importance for a decision.", description = "Returns the feature importance for a particular decision calculated using the lime algorithm.")
    public Response lime(@QueryParam("key") String decisionId) {
        Saliency saliency = explainabilityService.getFeatureImportace(decisionId);
        return Response.ok(new DecisionExplanationResponse()).build();
    }
}