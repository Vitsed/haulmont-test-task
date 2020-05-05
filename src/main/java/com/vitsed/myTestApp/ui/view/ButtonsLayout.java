package com.vitsed.myTestApp.ui.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vitsed.myTestApp.utils.names.Designation;

public class ButtonsLayout extends HorizontalLayout {

    private Button add;
    private Button edit;
    private Button delete;
    private Button ok;
    private Button cancel;

    public ButtonsLayout() {

    }

    public HorizontalLayout createViewButtonsLayout() {

        add = new Button(Designation.BUTTON_ADD);
        edit = new Button(Designation.BUTTON_EDIT);
        delete = new Button(Designation.BUTTON_DELETE);

        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        add.addClickShortcut(Key.ENTER);
        delete.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(add, edit, delete);
    }

    public HorizontalLayout createFormButtonsLayout() {

        ok = new Button(Designation.BUTTON_OK);
        cancel = new Button(Designation.BUTTON_CANCEL);

        ok.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);

        ok.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(ok, cancel);
    }
}
