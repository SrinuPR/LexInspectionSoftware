--LIS_CPMCS :

CREATE TABLE `mydb`.`lis_cpmcs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));
  
alter table mydb.lis_cpmcs
add column user_id int not null after subscriber_id;

alter table mydb.lis_cpmcs
add column customerpo_number int not null after user_id;

alter table mydb.lis_cpmcs
add column customerpo_date  timestamp not null after customerpo_number;

alter table mydb.lis_cpmcs
add column customerpo_quantity int not null after customerpo_date;

alter table mydb.lis_cpmcs
add column notes_po varchar(250) after customerpo_quantity;

alter table mydb.lis_cpmcs
add column created_timestamp timestamp not null after notes_po;

alter table mydb.lis_cpmcs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_cpmcs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_cpmcs
add column updated_by varchar (50) after updated_timestamp;
  
  --LIS_CPMCM
CREATE TABLE `mydb`.`lis_cpmcm` (
  `customerpo_number` INT NOT NULL,
  PRIMARY KEY (`customerpo_number`));

alter table mydb.lis_cpmcm
add column customerpo_date timestamp not null after customerpo_number;

alter table mydb.lis_cpmcm
add column customerpo_quantity int not null after customerpo_date;

alter table mydb.lis_cpmcm
add column notes_po varchar(50) after customerpo_quantity;  

alter table mydb.lis_cpmcm
add column created_timestamp timestamp not null after notes_po;

alter table mydb.lis_cpmcm
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_cpmcm
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_cpmcm
add column updated_by varchar(50) after updated_timestamp;


--LIS_ITMCS

CREATE TABLE `mydb`.`lis_itmcs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));
  
  alter table mydb.lis_itmcs
add column user_id int not null after subscriber_id;

alter table mydb.lis_itmcs
add column inspection_type_id int after user_id;

alter table mydb.lis_itmcs
add column inspection_type_name varchar(50) not null after inspection_type_id;

alter table mydb.lis_itmcs
add column created_timestamp timestamp not null after inspection_type_name;

alter table mydb.lis_itmcs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_itmcs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_itmcs
add column updated_by varchar(50) after updated_timestamp;

--LIS_ISMCS

CREATE TABLE `mydb`.`lis_ismcs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));

alter table mydb.lis_ismcs
add column user_id int not null after subscriber_id;

alter table mydb.lis_ismcs
add column inspection_stage_id int not null after user_id;

alter table mydb.lis_ismcs
add column inspection_stage_name varchar(100) after inspection_stage_id;

alter table mydb.lis_ismcs
add column created_timestamp timestamp not null after inspection_stage_name;

alter table mydb.lis_ismcs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_ismcs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_ismcs
add column updated_by varchar(50) after updated_timestamp;  

--LIS_FMACS

CREATE TABLE `mydb`.`lis_fmacs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));
  
alter table mydb.lis_fmacs
add column user_id int not null after subscriber_id;

alter table mydb.lis_fmacs
add column facility_machine_number int not null after user_id;

alter table mydb.lis_fmacs
add column facility_machine_name varchar(70) after facility_machine_number;

alter table mydb.lis_fmacs
add column created_timestamp timestamp not null after facility_machine_name;

alter table mydb.lis_fmacs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_fmacs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_fmacs
add column updated_by varchar(50) after updated_timestamp;


----LIS_SHMCS
CREATE TABLE `mydb`.`lis_shmcs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));
  
alter table mydb.lis_shmcs
add column user_id int not null after subscriber_id;

alter table mydb.lis_shmcs
add column shift_id int not null after user_id;

alter table mydb.lis_shmcs
add column shift_name varchar(70) after shift_id;

alter table mydb.lis_shmcs
add column created_timestamp timestamp not null after shift_name;

alter table mydb.lis_shmcs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_shmcs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_shmcs
add column updated_by varchar(50) after updated_timestamp;
 

--LIS_CMDCS

CREATE TABLE `mydb`.`lis_cmdcs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));
  
alter table mydb.lis_cmdcs
add column user_id int not null after subscriber_id;

alter table mydb.lis_cmdcs
add column component_product_drawing_num varchar(50) not null after user_id;

alter table mydb.lis_cmdcs
add column customer_name_address varchar(250) not null after component_product_drawing_num;

alter table mydb.lis_cmdcs
add column component_product_name varchar(50) not null after customer_name_address;

alter table mydb.lis_cmdcs
add column component_product_num varchar(50) not null after component_product_name;

alter table mydb.lis_cmdcs
add column component_product_material varchar(50) not null after component_product_num;

alter table mydb.lis_cmdcs
add column additional_notes varchar(250)  after component_product_material;

alter table mydb.lis_cmdcs
add column created_timestamp timestamp not null after additional_notes;

alter table mydb.lis_cmdcs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_cmdcs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_cmdcs
add column updated_by varchar(50) after updated_timestamp;


---LIS_WOMCS
CREATE TABLE `mydb`.`lis_womcs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));
    
alter table mydb.lis_womcs
add column user_id int not null after subscriber_id;

alter table mydb.lis_womcs
add column component_product_drawing_num varchar(50) not null after user_id;

alter table mydb.lis_womcs
add column customerpo_number varchar(50) not null after component_product_drawing_num;

alter table mydb.lis_womcs
add column work_job_order_num varchar(30) not null after customerpo_number;

alter table mydb.lis_womcs
add column work_job_order_date timestamp not null after work_job_order_num;

alter table mydb.lis_womcs
add column lot_number varchar(10) not null after work_job_order_date;

alter table mydb.lis_womcs
add column lot_size int not null after lot_number;

alter table mydb.lis_womcs
add column lot_size_units int not null after lot_size;

alter table mydb.lis_womcs
add column manufacturing_batch_num varchar(30) not null after lot_size_units;

alter table mydb.lis_womcs
add column manufacturing_batch_size int not null after manufacturing_batch_num;

alter table mydb.lis_womcs
add column manufacturing_batch_units varchar(5) after manufacturing_batch_size;

alter table mydb.lis_womcs
add column notes varchar(250) after manufacturing_batch_units;

alter table mydb.lis_womcs
add column created_timestamp timestamp not null after notes;

alter table mydb.lis_womcs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_womcs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_womcs
add column updated_by varchar(50) after updated_timestamp;


--LIS_INMDC
CREATE TABLE `mydb`.`lis_inmdc` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));

alter table mydb.lis_inmdc
add column user_id int not null after subscriber_id;

alter table mydb.lis_inmdc
add column component_product_drawing_num varchar(50) not null after user_id;

alter table mydb.lis_inmdc
add column customer_name_address varchar(250) not null after component_product_drawing_num;

alter table mydb.lis_inmdc
add column component_product_name varchar(50) not null after customer_name_address;

alter table mydb.lis_inmdc
add column component_product_num varchar(50) not null after component_product_name;

alter table mydb.lis_inmdc
add column component_product_material varchar(50) not null after component_product_num;

alter table mydb.lis_inmdc
add column additional_notes varchar(250) not null after component_product_material;

alter table mydb.lis_inmdc
add column inspection_type_name varchar(30) not null after additional_notes;

alter table mydb.lis_inmdc
add column inspection_stage_id int not null after inspection_type_name;

alter table mydb.lis_inmdc
add column created_timestamp timestamp not null after inspection_stage_id;

alter table mydb.lis_inmdc
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_inmdc
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_inmdc
add column updated_by varchar(50) after updated_timestamp; 

---LIS_ILIMC
CREATE TABLE `mydb`.`lis_ilimc` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));

alter table mydb.lis_ilimc
add column user_id int not null after subscriber_id;

alter table mydb.lis_ilimc
add column component_product_drawing_num varchar(50) not null after user_id;

alter table mydb.lis_ilimc
add column created_timestamp timestamp not null after component_product_drawing_num;

alter table mydb.lis_ilimc
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_ilimc
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_ilimc
add column updated_by varchar(50) after updated_timestamp;


---LIS_IRMCS
CREATE TABLE `mydb`.`lis_irmcs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));
  

alter table mydb.lis_irmcs
add column user_id int not null after subscriber_id;

alter table mydb.lis_irmcs
add column component_product_drawing_num varchar(50) not null after user_id;

alter table mydb.lis_irmcs
add column component_product_name varchar(50) not null after component_product_drawing_num;

alter table mydb.lis_irmcs
add column work_job_order_num varchar(30) not null after component_product_name;

alter table mydb.lis_irmcs
add column lot_number varchar(10) not null after work_job_order_num;

alter table mydb.lis_irmcs
add column lot_size int not null after lot_number;

alter table mydb.lis_irmcs
add column manufacturing_batch_num varchar(30) not null after lot_size;

alter table mydb.lis_irmcs
add column manufacturing_batch_size int not null after manufacturing_batch_num;

alter table mydb.lis_irmcs
add column inspection_type_name varchar(30) not null after manufacturing_batch_size;

alter table mydb.lis_irmcs
add column inspection_stage_id int not null after inspection_type_name;

alter table mydb.lis_irmcs
add column inspection_report_num varchar(30) not null after inspection_stage_id;

alter table mydb.lis_irmcs
add column created_timestamp timestamp not null after inspection_report_num;

alter table mydb.lis_irmcs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_irmcs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_irmcs
add column updated_by varchar(50) after updated_timestamp;


---LIS_IMDES
CREATE TABLE `mydb`.`lis_imdes` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));


alter table mydb.lis_imdes
add column user_id int not null after subscriber_id;

alter table mydb.lis_imdes
add column component_product_drawing_num varchar(50) not null after user_id;

alter table mydb.lis_imdes
add column inspection_report_num varchar(30) not null after component_product_drawing_num;

alter table mydb.lis_imdes
add column work_job_order_num varchar(30) not null after inspection_report_num;

alter table mydb.lis_imdes
add column lot_number varchar(10) not null after work_job_order_num;

alter table mydb.lis_imdes
add column lot_size int not null after lot_number;

alter table mydb.lis_imdes
add column manufacturing_batch_num varchar(30) not null after lot_size;

alter table mydb.lis_imdes
add column manufacturing_batch_size int not null after manufacturing_batch_num;

alter table mydb.lis_imdes
add column component_product_name varchar(50) not null after manufacturing_batch_size;

alter table mydb.lis_imdes
add column inspection_type_name varchar(30) not null after component_product_name;

alter table mydb.lis_imdes
add column inspection_stage_id int not null after inspection_type_name;

alter table mydb.lis_imdes
add column facility_machine_number int not null after inspection_stage_id;

alter table mydb.lis_imdes
add column facility_machine_name varchar(150) after facility_machine_number;

alter table mydb.lis_imdes
add column user_name varchar(150) after facility_machine_name;

alter table mydb.lis_imdes
add column inspection_date timestamp after user_name;

alter table mydb.lis_imdes
add column shift_id int not null after inspection_date;

alter table mydb.lis_imdes
add column part_identification_num varchar(30) not null after shift_id;

alter table mydb.lis_imdes
add column actual_measurement decimal not null after part_identification_num;

alter table mydb.lis_imdes
add column actual_base_measure varchar(5) after actual_measurement;

alter table mydb.lis_imdes
add column actual_upper_limit decimal not null after actual_base_measure;

alter table mydb.lis_imdes
add column actual_lower_limit decimal not null after actual_upper_limit;

alter table mydb.lis_imdes
add column devation decimal not null after actual_lower_limit;

alter table mydb.lis_imdes
add column accept_reject varchar(8) after devation;

alter table mydb.lis_imdes
add column inspectd_quantity int not null after accept_reject;

alter table mydb.lis_imdes
add column product_quantity int not null after inspectd_quantity;

alter table mydb.lis_imdes
add column created_timestamp timestamp not null after product_quantity;

alter table mydb.lis_imdes
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_imdes
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_imdes
add column updated_by varchar(50) after updated_timestamp;  


----LIS_SUMAS
CREATE TABLE `mydb`.`lis_sumas` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));

alter table mydb.lis_sumas
add column user_id int not null after subscriber_id;

alter table mydb.lis_sumas
add column subscriber_name varchar(50) not null after user_id;

alter table mydb.lis_sumas
add column subscriber_address varchar(250) after subscriber_name;  

alter table mydb.lis_sumas
add column created_timestamp timestamp not null after subscriber_address;

alter table mydb.lis_sumas
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_sumas
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_sumas
add column updated_by varchar(50) after updated_timestamp; 


----LIS_UMACS
CREATE TABLE `mydb`.`lis_umacs` (
  `user_id` INT NOT NULL,
  PRIMARY KEY (`user_id`));


alter table mydb.lis_umacs
add column subscriber_id int not null after user_id;

alter table mydb.lis_umacs
add column user_type_id varchar(30) not null after subscriber_id;  

alter table mydb.lis_umacs
add column user_id  varchar(10) not null after user_type_id; 

alter table mydb.lis_umacs
add column user_name  varchar(20) not null after user_id; 

alter table mydb.lis_umacs
add column old_password2  varchar(20) not null after user_name; 

alter table mydb.lis_umacs
add column old_password1  varchar(20) not null after old_password2; 

alter table mydb.lis_umacs
add column active_password  varchar(20) not null after old_password1; 

alter table mydb.lis_umacs
add column created_timestamp timestamp not null after active_password;

alter table mydb.lis_umacs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_umacs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_umacs
add column updated_by varchar(50) after updated_timestamp; 


----LIS_UTMCS
CREATE TABLE `mydb`.`lis_utmcs` (
  `subscriber_id` INT NOT NULL,
  PRIMARY KEY (`subscriber_id`));
  
alter table mydb.lis_umacs
add column user_type_id int not null after subscriber_id; 

alter table mydb.lis_umacs
add column user_type_name varchar(30) not null after user_type_id; 

alter table mydb.lis_umacs
add column created_timestamp timestamp not null after user_type_name;

alter table mydb.lis_umacs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_umacs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_umacs
add column updated_by varchar(50) after updated_timestamp; 


----LIS_LOGIN
CREATE TABLE `mydb`.`lis_login` (
  `login_id` INT NOT NULL,
  PRIMARY KEY (`login_id`));
  
alter table mydb.lis_login
add column user_id  int not null after login_id; 

alter table mydb.lis_login
add column subscriber_id int not null after user_id

alter table mydb.lis_umacs
add column created_timestamp timestamp not null after subscriber_id;

alter table mydb.lis_umacs
add column created_by varchar(50) not null after created_timestamp;

alter table mydb.lis_umacs
add column updated_timestamp timestamp after created_by;

alter table mydb.lis_umacs
add column updated_by varchar(50) after updated_timestamp; 
