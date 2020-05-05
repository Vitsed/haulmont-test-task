package com.vitsed.myTestApp.ui.view.group;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.StudentGroup;
import com.vitsed.myTestApp.backend.service.GroupService;
import com.vitsed.myTestApp.ui.MainLayout;

@Route(value = "groups", layout = MainLayout.class)
@PageTitle("Groups | Student App")
public class GroupView extends VerticalLayout {

    private final GroupService groupService;
    private Grid<StudentGroup> grid = new Grid<>();

    public GroupView(GroupService groupService) {
        this.groupService = groupService;

        add(new H1("Group list will be here"));
    }

    private void configureGrid() {
        grid.addClassName("group-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(StudentGroup::getNumber).setHeader("Номер группы").setSortable(true);
        grid.addColumn(StudentGroup::getFacultyName).setHeader("Факультет");
    }

}
