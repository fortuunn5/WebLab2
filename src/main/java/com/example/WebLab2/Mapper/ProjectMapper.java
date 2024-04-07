package com.example.WebLab2.Mapper;

import com.example.WebLab2.Dto.ProjectDto;
import com.example.WebLab2.Model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMapper {
    private final TaskMapper taskMapper;

    public ProjectDto map(Project project) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            ProjectDto projectDto = ProjectDto.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .description(project.getDescription())
                    .startDate(project.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .endDate(project.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .tasks(taskMapper.map(project.getTasks()))
                    .build();
            return projectDto;
        } catch (Exception e) {
            return null;
        }

    }

    public List<ProjectDto> map(List<Project> projects) {
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            projectDtoList.add(map(projects.get(i)));
        }
        return projectDtoList;
    }

    public Project map(ProjectDto projectDto) {
        Project project = Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .startDate(projectDto.getStartDate() == null ? null :Date.from(projectDto.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .endDate(projectDto.getEndDate() == null ? null : Date.from(projectDto.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .tasks(taskMapper.mapp(projectDto.getTasks()))
                .build();
        return project;
    }
}
