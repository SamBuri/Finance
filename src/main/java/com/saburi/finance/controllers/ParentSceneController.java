package com.saburi.finance.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.saburi.common.utils.FXUIUtils;
import static com.saburi.common.utils.Navigation.loadSearchEngine;

import com.saburi.finance.utils.FinanceSearchTree;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author ClinicMaster13
 */
public class ParentSceneController extends FinanceSceneController{


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
           init();
           loadSearchEngine(mnuSearchEngine, new FinanceSearchTree().getTreeItems(), true);

        } catch (IOException ex) {
            FXUIUtils.errorMessage(ex);
        }
    }

}
