package com.springboot.hello.dao;

import com.springboot.hello.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HospitalDao {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public HospitalDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }
    RowMapper<Hospital> rowMapper = (rs, rowNum) ->{
        Hospital hospital = new Hospital();
        hospital.setId(rs.getInt("id"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setOpenLocalGovernmentCode(rs.getInt("open_local_goverment_code"));
        hospital.setManagementNumber(rs.getString("management_number"));
        // 날짜
        hospital.setLicenseDate(rs.getTimestamp("license_date").toLocalDateTime());
        hospital.setBusinessStatus(rs.getInt("business_status"));
        hospital.setBusinessStatusCode(rs.getInt("business_status_code"));
        hospital.setPhone(rs.getString("phone"));
        hospital.setFullAddress(rs.getString("full_address"));
        hospital.setRoadNameAddress(rs.getString("road_name_address"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        hospital.setBusinessTypeName(rs.getString("business_type_name"));
        hospital.setHealthcareProviderCount(rs.getInt("healthcare_provider_count"));
        hospital.setPatientRoomCount(rs.getInt("patient_room_count"));
        hospital.setTotalNumberOfBeds(rs.getInt("total_number_of_beds"));
        hospital.setTotalAreaSize(rs.getFloat("total_area_size"));

        return hospital;
    };
    public Hospital findById(int id){
        return this.jdbcTemplate.queryForObject("select * from nation_wide_hospital where id =?", rowMapper, id);
    }
    public void deleteAll() {
        this.jdbcTemplate.update("delete from nation_wide_hospital");
    }
    public int getCount() {
        String sql = "select count(id) from nation_wide_hospital;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public void add(Hospital hospital){
        /*this.jdbcTemplate.update("INSERT INTO nation_wide_hospital(id, openServiceName, openLocalGovernmentCode, " +
                "managementNumber, licenseDate, businessStatus, businessStatusCode, phone, fullAddress, roadNameAddress, " +
                "hospitalName, businessTypeName, healthcareProviderCount, patientRoomCount, totalNumberOfBeds, totalAreaSize )" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");*/
        String sql = "INSERT INTO `like-lion-db`.`nation_wide_hospital`(`id`, `open_service_name`, `open_local_goverment_code`, `management_number`, \n" +
                "`license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, \n" +
                "`road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, \n" +
                "`patient_room_count`, `total_number_of_beds`, `total_area_size`) \n" +
                "VALUES(?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?);";
        jdbcTemplate.update(sql,
                hospital.getId(),hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(), hospital.getManagementNumber(),
                hospital.getLicenseDate(), hospital.getBusinessStatus(), hospital.getBusinessStatusCode(),
                hospital.getPhone(),hospital.getFullAddress(),hospital.getRoadNameAddress(),
                hospital.getHospitalName(), hospital.getBusinessTypeName(), hospital.getHealthcareProviderCount(),
                hospital.getPatientRoomCount(),hospital.getTotalNumberOfBeds(),hospital.getTotalAreaSize()
        );
    }
}
