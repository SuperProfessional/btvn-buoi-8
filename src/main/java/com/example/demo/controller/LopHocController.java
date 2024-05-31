package com.example.demo.controller;


import com.example.demo.entities.LopHoc;
import com.example.demo.repositories.LopHocRepository;
import com.example.demo.request.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "lop-hoc")
@RequiredArgsConstructor
public class LopHocController {

  private final LopHocRepository lopHocRepository;

  @RequestMapping(value = "/hien-thi-tat-ca", method = {RequestMethod.GET, RequestMethod.POST})
  public String hienThi(Model model) {
    model.addAttribute("lopHocList", this.lopHocRepository.findAll());
    return "lop-hoc";
  }

  @GetMapping("/tim-kiem")
  public String search(Model model, @ModelAttribute Search search) {
    model.addAttribute("lopHocList", this.lopHocRepository.findAllByNameLikeAndLocationLike("%" + search.getTen() + "%", "%" + search.getDiaDiem() + "%"));
    return "lop-hoc";
  }

  @GetMapping("/remove/{id}")
  public String remove(@PathVariable(name = "id") Long id) {
    this.lopHocRepository.deleteById(id);
    return "forward:/lop-hoc/hien-thi-tat-ca";
  }

  @GetMapping("/view-update/{id}")
  public String viewUpdate(Model model, @PathVariable(name = "id") Long id) {
    model.addAttribute("isUpdate", Boolean.TRUE);
    model.addAttribute(
        "lopHoc",
        this.lopHocRepository.findById(id)
            .orElse(new LopHoc())
    );
    return "forward:/lop-hoc/hien-thi-tat-ca";
  }

  @PostMapping("/update/{id}")
  public String update(Model model,
                       @PathVariable(name = "id") Long id,
                       @ModelAttribute(name = "lopHoc") LopHoc lopHoc) {

//    if (!this.validate(model, lopHoc)) {
    this.lopHocRepository.findById(id)
        .ifPresent(
            lh -> {
              lh.setName(lopHoc.getName());
              lh.setLocation(lopHoc.getLocation());

              this.lopHocRepository.save(lh);
            }
        );
    return "redirect:/lop-hoc/hien-thi-tat-ca";
//    }
//    model.addAttribute("isUpdate", Boolean.TRUE);
//    model.addAttribute("lopHoc", lopHoc);
//    return "forward:/lop-hoc/hien-thi-tat-ca";
  }

  @PostMapping("/add")
  public String add(Model model,
                    @ModelAttribute LopHoc lopHoc) {

//    if (!this.validate(model, lopHoc)) {
    this.lopHocRepository.save(lopHoc);
    return "redirect:/lop-hoc/hien-thi-tat-ca";
//    }
//    model.addAttribute("lopHoc", lopHoc);
//    return "forward:/lop-hoc/hien-thi-tat-ca";
  }

//  private boolean validate(Model model, lopHoc lopHoc) {
//    Map<String, List<String>> rerult = this.validator.validate(lopHoc)
//        .stream()
//        .collect(
//            Collectors.groupingBy(
//                o -> o.getPropertyPath().toString(),
//                Collectors.mapping(
//                    ConstraintViolation::getMessage, Collectors.toList()
//                )
//            )
//        );
//
//    if (!CollectionUtils.isEmpty(rerult)) {
//      model.addAttribute("messageError", rerult);
//      return Boolean.TRUE;
//    }
//    return Boolean.FALSE;
//  }

}
