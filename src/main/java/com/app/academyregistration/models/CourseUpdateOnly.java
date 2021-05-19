package com.app.academyregistration.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseUpdateOnly {
    private List<Long> professors;

    private List<Long> students;
}
