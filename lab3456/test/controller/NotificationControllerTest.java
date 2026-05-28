//package org.example.controller;
//
//import org.example.service.NotificationMapper;
//import org.example.service.NotificationService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(NotificationController.class)
//class NotificationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private NotificationService notificationService;
//
//    @MockBean
//    private NotificationMapper notificationMapper;
//
//    @Test
//    void shouldReturnOkForNotificationsAll() throws Exception {
//        mockMvc.perform(get("/notifications/all"))
//                .andExpect(status().isOk());
//    }
//}
