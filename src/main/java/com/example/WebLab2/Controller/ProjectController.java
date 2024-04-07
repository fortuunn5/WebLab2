package com.example.WebLab2.Controller;

import com.example.WebLab2.Dto.ProjectDto;
import com.example.WebLab2.Service.ProjectService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto newProject) {
        return new ResponseEntity<>(projectService.createProject(newProject), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.readProject(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getFilter(@RequestParam(name = "search", required = false) String search) {
        if(StringUtils.isBlank(search))
            return ResponseEntity.ok(projectService.readAllProjects());
        return ResponseEntity.ok(projectService.filter(search));
    }

    @GetMapping("/notClosed")
    public ResponseEntity<Map<Long, Integer>> getInf() {
        return ResponseEntity.ok(projectService.information());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto upProject) {
        return ResponseEntity.ok(projectService.updateProject(id, upProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
