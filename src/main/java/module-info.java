/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

open module com.saburi.Finance {
    requires com.saburi.Common;
    requires org.hibernate.orm.core;
    requires org.jboss.logging;
    requires javassist;
    requires net.bytebuddy;
    requires antlr;
    requires java.transaction;
    requires jandex;
    requires com.fasterxml.classmate;
    requires java.activation;
    requires dom4j;
    requires org.hibernate.commons.annotations;
    requires java.xml.bind;
    requires java.persistence;
    requires org.hibernate.orm.envers;
    requires org.hibernate.validator;
    requires java.validation;
    requires javax.el;
    requires com.sun.xml.txw2;
    requires com.sun.istack.runtime;
    requires mysql.connector.java;
    requires protobuf.java;
    requires itextpdf;
    requires org.kordamp.desktoppanefx.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires javafx.swing;
    requires fontawesomefx;

    exports com.saburi.finance.main;
    exports com.saburi.finance.entities;
    exports com.saburi.finance.dbaccess;
    exports com.saburi.finance.utils;
    exports com.saburi.finance.controllers;
}
