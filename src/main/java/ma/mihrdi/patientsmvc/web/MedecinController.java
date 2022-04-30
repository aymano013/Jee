package ma.mihrdi.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.mihrdi.patientsmvc.entities.Medecin;
import ma.mihrdi.patientsmvc.entities.Patient;
import ma.mihrdi.patientsmvc.repositories.MedecinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class MedecinController {

    private MedecinRepository medecinRepository;


    @GetMapping(path = "/user/medecin")
    public String medecin(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page ,
                          @RequestParam(name = "size",defaultValue = "5") int size,
                          @RequestParam(name = "keyword",defaultValue = "") String  keyword){
        Page<Medecin> pageMedecin = medecinRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listMedecin" , pageMedecin.getContent());
        model.addAttribute("pages",new int[pageMedecin.getTotalPages()] );
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "medecin";
    }
    @GetMapping(path = "/admin/medecin/delete")
    public String delete(Long id , String keyword , int page){
        medecinRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
   /* @GetMapping(path = "/")
    public String home(){
        return "redirect:/user/index";
    }*/
    /*@GetMapping(path = "/user/medecin")
    @ResponseBody
    public List<Medecin> listMedecin(){
        return  medecinRepository.findAll();
    }*/

    @GetMapping(path = "/admin/formMedecin")
    public String formMedecin(Model model){
        model.addAttribute("medecin",new Medecin());
        return "formMedecin";
    }
    @PostMapping(path = "/admin/medecin/save")
    public String save(Model model , @Valid Medecin medecin, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formMedecin";
        medecinRepository.save(medecin);
        return "redirect:/user/medecin?page"+page+"keyword"+keyword;
    }

    @GetMapping(path = "/admin/editMedecin")
    public String edit(Model model,Long id ,String keyword , int page){
        Medecin medecin = medecinRepository.findById(id).orElse(null);
        if (medecin==null) throw new RuntimeException("Medecin introuvable");
        model.addAttribute("medecin",medecin);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);

        return "editMedecin";
    }

}
