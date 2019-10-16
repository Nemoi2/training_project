package bellintegrator.training.controller;

import bellintegrator.training.service.OfficeService;
import bellintegrator.training.view.OfficesView;
import bellintegrator.training.view.OfficeView;
import bellintegrator.training.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(final OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Получить офис", httpMethod = "GET")
    @GetMapping("/{id}")
    public OfficeView getOffice(@PathVariable("id") final Long id) {
        return officeService.getOffice(id);
    }

    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void addOffice(@RequestBody final OfficesView officesView) {
        officeService.addOffice(officesView);
    }

    @ApiOperation(value = "Обновить офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void updateOffice(@RequestBody final OfficeView officeView) {
        officeService.updateOffice(officeView);
    }

    @JsonView(View.class)
    @ApiOperation(value = "Получить список офисов", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/list")
    public List<OfficesView> offices(@RequestBody final OfficesView officesView) {
        return officeService.offices(officesView);
    }
}
