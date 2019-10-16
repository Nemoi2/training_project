package bellintegrator.training.controller;

import bellintegrator.training.service.HandbookService;
import bellintegrator.training.view.DocView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api", produces = APPLICATION_JSON_VALUE)
public class HandbookController {

    private final HandbookService handbookService;

    @Autowired
    public HandbookController(final HandbookService handbookService) {
        this.handbookService = handbookService;
    }

    @ApiOperation(value = "Получить документы", httpMethod = "GET")
    @GetMapping("/docs")
    public List<DocView> docs() {
        return handbookService.docs();
    }

    @ApiOperation(value = "Получить страны", httpMethod = "GET")
    @GetMapping("/countries")
    public List<DocView> countries() {
        return handbookService.contries();
    }
}