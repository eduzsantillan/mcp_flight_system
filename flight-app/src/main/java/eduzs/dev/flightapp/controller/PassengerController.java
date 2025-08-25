package eduzs.dev.flightapp.controller;

import eduzs.dev.flightapp.model.Passenger;
import eduzs.dev.flightapp.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public String getAllPassengers(Model model) {
        List<Passenger> passengers = passengerService.getAllPassengers();
        model.addAttribute("passengers", passengers);
        return "passengers/list";
    }

    @GetMapping("/{id}")
    public String getPassengerDetails(@PathVariable Long id, Model model) {
        Optional<Passenger> passenger = passengerService.findPassengerById(id);
        
        if (passenger.isEmpty()) {
            return "redirect:/passengers";
        }
        
        model.addAttribute("passenger", passenger.get());
        return "passengers/view";
    }

    @GetMapping("/new")
    public String showNewPassengerForm(Model model) {
        model.addAttribute("passenger", new Passenger(null, "", "", "", "", null));
        return "passengers/form";
    }

    @PostMapping
    public String createPassenger(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam(required = false) String passportNumber,
            RedirectAttributes redirectAttributes) {
        try {
            Passenger passenger = passengerService.createPassenger(firstName, lastName, email, phone, passportNumber);
            redirectAttributes.addFlashAttribute("successMessage", "Passenger created successfully! ID: " + passenger.id());
            return "redirect:/passengers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create passenger: " + e.getMessage());
            return "redirect:/passengers/new";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditPassengerForm(@PathVariable Long id, Model model) {
        Optional<Passenger> passenger = passengerService.findPassengerById(id);
        
        if (passenger.isEmpty()) {
            return "redirect:/passengers";
        }
        
        model.addAttribute("passenger", passenger.get());
        return "passengers/edit";
    }

    @PostMapping("/{id}/update")
    public String updatePassenger(
            @PathVariable Long id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam(required = false) String passportNumber,
            RedirectAttributes redirectAttributes) {
        try {
            passengerService.updatePassenger(id, firstName, lastName, email, phone, passportNumber);
            redirectAttributes.addFlashAttribute("successMessage", "Passenger updated successfully!");
            return "redirect:/passengers/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update passenger: " + e.getMessage());
            String editPath = "/passengers/" + id + "/edit";
            return "redirect:" + editPath;
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePassenger(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            passengerService.deletePassenger(id);
            redirectAttributes.addFlashAttribute("successMessage", "Passenger deleted successfully!");
            return "redirect:/passengers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete passenger: " + e.getMessage());
            return "redirect:/passengers";
        }
    }
}
