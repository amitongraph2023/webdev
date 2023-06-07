package com.cms.webdev.controller;

import com.cms.webdev.common.annotations.BaseURL;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = BaseURL.class)
public class BaseUrlAdvice { }