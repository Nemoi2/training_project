package bellintegrator.training.controller;

import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.service.OrganizationService;
import bellintegrator.training.view.OrganizationView;
import bellintegrator.training.view.OrganizationsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    private final OrganizationService organizationService;

    private final MapperFacade mapperFacade;

    @Autowired
    public MainController(final OrganizationService organizationService, final MapperFacade mapperFacade) {
        this.organizationService = organizationService;
        this.mapperFacade = mapperFacade;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String greeting() {
        return "index";
    }

    @RequestMapping(value = {"/organization"}, method = RequestMethod.GET)
    public String organization() {
        return "organization";
    }

    @RequestMapping(value = {"/organization/getOrg"}, method = RequestMethod.GET)
    public String getOrgGet(Model model) {
        OrganizationView organizationView = new OrganizationView();
        model.addAttribute("organizationView", organizationView);
        return "/organization/getOrg";
    }

    @PostMapping("/organization/getOrg")
    public String getOrgPost(@ModelAttribute OrganizationView organization, Model model) {

        OrganizationView organizationView = organizationService.getOrganization(organization.id);
        model.addAttribute("organizationView", organizationView);
        return "/organization/resultGet";
    }

    @RequestMapping(value = {"/organization/addOrg"}, method = RequestMethod.GET)
    public String addOrgGet(Model model) {
        OrganizationView organizationView = new OrganizationView();
        model.addAttribute("organizationView", organizationView);
        return "/organization/addOrg";
    }

    @PostMapping("/organization/addOrg")
    public String addOrgPost(@ModelAttribute OrganizationView organizationView) {
        organizationService.addOrganization(organizationView);
        return "result";
    }

    @RequestMapping(value = {"/organization/updateOrg"}, method = RequestMethod.GET)
    public String updateOrgGet(Model model) {
        OrganizationView organizationView = new OrganizationView();
        model.addAttribute("organizationView", organizationView);
        return "/organization/updateOrg";
    }

    @PostMapping("/organization/updateOrg")
    public String updateOrgPost(@ModelAttribute OrganizationView organizationView) {
        organizationService.updateOrganization(organizationView);
        return "result";
    }

    @RequestMapping(value = {"/organization/listOrg"}, method = RequestMethod.GET)
    public String listOrgGet(Model model) {
        OrganizationsView organizationsView = new OrganizationsView();
        model.addAttribute("organizationsView", organizationsView);
        return "/organization/listOrg";
    }

    @PostMapping("/organization/listOrg")
    public String listOrgPost(@ModelAttribute OrganizationsView organizationsView, Model model) {

        model.addAttribute("organizationView", organizationService.organizations(organizationsView));
        return "/organization/resultList";
    }
}
