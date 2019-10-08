package bellintegrator.training.controller;

import bellintegrator.training.service.OrganizationService;
import bellintegrator.training.view.OrganizationsView;
import bellintegrator.training.view.OrganizationView;
import bellintegrator.training.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(final OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Получить организацию", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationView getOrganization(@PathVariable("id") final Long id) {
        return organizationService.getOrganization(id);
    }

    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void addOrganization(@RequestBody final OrganizationView organizationView) {
        organizationService.addOrganization(organizationView);
    }

    @ApiOperation(value = "Обновить организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void updateOrganization(@RequestBody final OrganizationView organizationView) {
        organizationService.updateOrganization(organizationView);
    }

    @JsonView(View.class)
    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/list")
    public List<OrganizationsView> organizations(@RequestBody final OrganizationsView organizationsView) {
        return organizationService.organizations(organizationsView);
    }
}
