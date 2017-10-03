# LMS

Table Creation:

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(45) NOT NULL,
  `dob` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `doj` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `designation` varchar(45) DEFAULT NULL,
  `direct_reporter_id` int(11) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_date` timestamp NULL DEFAULT NULL,
  `marital_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `leave` (
  `leave_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NOT NULL,
  `leave_type` varchar(45) NOT NULL,
  `earnend_leaves` int(11) DEFAULT NULL,
  `casual_leaves` int(11) DEFAULT NULL,
  `leave_balance` int(11) DEFAULT NULL,
  `leave_taken` int(11) DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `no_of_days` int(11) DEFAULT NULL,
  `requested_to` varchar(50) DEFAULT NULL,
  `requested_on` timestamp NULL DEFAULT NULL,
  `approved_on` timestamp NULL DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `is_approved` int(11) DEFAULT NULL,
  `approved_by` int(11) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`leave_id`),
  KEY `employee_id_idx` (`emp_id`),
  CONSTRAINT `empl_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`login_id`),
  KEY `employee_id_idx` (`emp_id`),
  CONSTRAINT `emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

