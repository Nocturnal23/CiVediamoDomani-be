INSERT INTO users (user_id, created_date, modified_date, deleted, url, app_role, email, first_name, last_name, password) VALUES (USERS_ID_SEQ.nextval, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'admin', 1, 'admin@admin', 'Admin', 'Admin', '$2a$10$wEzruOhCiFvEvb/wo8W.zOWLvmm.u42OkkJyd4Q7IQ1CcMC1uUvaK');
INSERT INTO users (user_id, created_date, modified_date, deleted, url, app_role, email, first_name, last_name, password) VALUES (USERS_ID_SEQ.nextval, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'normal', 2, 'user@user', 'UserN', 'UserC', '$2a$10$BItqTNuv4GGnD1eovmtnN.ehfSuXFpqbn9b6y7MnLOV.bSnBjwOFS');