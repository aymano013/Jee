package ma.mihrdi.patientsmvc.web;

import ma.mihrdi.patientsmvc.entities.Medecin;
import ma.mihrdi.patientsmvc.entities.RendezVous;
import ma.mihrdi.patientsmvc.repositories.MedecinRepository;
import ma.mihrdi.patientsmvc.repositories.PatientRepository;
import ma.mihrdi.patientsmvc.repositories.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RendezVousController {
    private RendezVousRepository RendezVousRepository;
    public RendezVousController(RendezVousRepository RendezVousRepository) {
        this.RendezVousRepository = RendezVousRepository;
    }

    @GetMapping(path = "/user/rdv")
    public String RendezVous(Model model,
                             @RequestParam(name = "page",defaultValue = "0") int page ,
                             @RequestParam(name = "size",defaultValue = "5") int size,
                             @RequestParam(name = "keyword",defaultValue = "") String  keyword){
        Page<RendezVous> pagerdv = RendezVousRepository.findByTitreContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listrdv" , pagerdv.getContent());
        model.addAttribute("pages",new int[pagerdv.getTotalPages()] );
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "rendezvous";
    }
}
