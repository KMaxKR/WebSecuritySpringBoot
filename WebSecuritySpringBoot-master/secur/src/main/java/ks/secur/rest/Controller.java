package ks.secur.rest;



import ks.secur.entity.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/dev")
public class Controller {
    List<Developer> dev = Stream.of(
            new Developer(1L, "First", "Last"),
            new Developer(2L, "First", "Last"),
            new Developer(3L, "First", "Last")
    ).collect(Collectors.toList());

    @GetMapping
//    @PreAuthorize("hasAuthority('developers:read')")
    public List<Developer> getAll(){
        return dev;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public void addDeveloper(@RequestBody Developer developer){
        dev.add(developer);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('developers:write')")
    public Developer getById(@PathVariable Long id){
        return dev.stream()
                .filter(developer -> developer.getId().equals(id))
                .findFirst().orElse(null);
    }
}
