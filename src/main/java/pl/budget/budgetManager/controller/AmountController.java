package pl.budget.budgetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.budget.budgetManager.model.Amount;
import pl.budget.budgetManager.service.AmountService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.regex.Pattern;

@Controller
public class AmountController {

    @Autowired
    private AmountService amountService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("amountList", amountService.getAllAmounts());
        model.addAttribute("retSum", amountService.returnSum());
        return "index";
    }

    @GetMapping("/onlyIn")
    public String onlyIn(Model model) {
        model.addAttribute("amountIn", amountService.getOnlyIn());
        model.addAttribute("retSumIn", amountService.returnSumIn());
        return "/viewsOnly/only_in";
    }

    @GetMapping("/onlyOut")
    public String onlyOut(Model model) {
        model.addAttribute("amountOut", amountService.getOnlyOut());
        model.addAttribute("retSumOut", amountService.returnSumOut());
        return "/viewsOnly/only_out";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model) {
        Amount amount = new Amount();
        model.addAttribute("amount", amount);
        return "new_amount";
    }

    @PostMapping("/saveAmount")
    public String saveAmount(@ModelAttribute("amount") Amount amount, BindingResult result) {
            amountService.saveAmount(amount);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {
        Amount amount = amountService.getAmountById(id);
        model.addAttribute("amount", amount);
        return "update_amount";
    }

    @GetMapping("/deleteAmount/{id}")
    public String deleteAmount(@PathVariable(value = "id") long id) {
        this.amountService.deleteAmoutById(id);
        return "redirect:/";
    }

    @GetMapping("/sortByAmount")
    public String sortByAmount(Model model) {
        model.addAttribute("sortByAmount", amountService.sortByAmount());
        model.addAttribute("retSum", amountService.returnSum());
        return "/sorts/sort_by_amount";
    }

    @GetMapping("/sortByAmountReversed")
    public String sortByAmountReversed(Model model) {
        model.addAttribute("sortByAmountReversed", amountService.sortByAmountReversed());
        return "/sorts/sort_by_amount_reversed";
    }

    @GetMapping("/sortOnlyInByAmount")
    public String sortOnlyInByAmount(Model model) {
        model.addAttribute("sortOnlyInByAmount", amountService.sortOnlyInByAmount());
        model.addAttribute("retSumIn", amountService.returnSumIn());
        return "/sorts/sort_only_in_by_amount";
    }
    @GetMapping("/sortOnlyInByAmountReserved")
    public String sortOnlyInByAmountReserved(Model model) {
        model.addAttribute("sortOnlyInByAmountReserved", amountService.sortOnlyInByAmountReserved());
        model.addAttribute("retSumIn", amountService.returnSumIn());
        return "/sorts/sort_only_in_by_amount_reserved";
    }
    @GetMapping("/sortOnlyOutByAmount")
    public String sortOnlyOutByAmount(Model model) {
        model.addAttribute("sortOnlyOutByAmount", amountService.sortOnlyOutByAmount());
        model.addAttribute("retSumOut", amountService.returnSumOut());
        return "/sorts/sort_only_out_by_amount";
    }
    @GetMapping("/sortOnlyOutByAmountReserved")
    public String sortOnlyOutByAmountReserved(Model model) {
        model.addAttribute("sortOnlyOutByAmountReserved", amountService.sortOnlyOutByAmountReserved());
        model.addAttribute("retSumOut", amountService.returnSumOut());
        return "/sorts/sort_only_out_by_amount_reserved";
    }



}
