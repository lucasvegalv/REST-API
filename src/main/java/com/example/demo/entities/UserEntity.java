package com.example.demo.entities;

import com.example.demo.dtos.UserDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column
        private String username;

        @Column
        private String password;

        @Column
        private String email;

        // Getters and Setters

        public int getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

}
