package eduzs.dev.flightapp.controller;

import eduzs.dev.flightapp.model.City;
import eduzs.dev.flightapp.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CityService cityService;

    public HomeController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "home";
    }
}
