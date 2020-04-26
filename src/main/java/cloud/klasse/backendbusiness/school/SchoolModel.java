package cloud.klasse.backendbusiness.school;

public interface SchoolModel {

    String getName();

    void setName(String name);

    String getStreet();

    void setStreet(String street);

    Integer getZip();

    void setZip(Integer zip);

    String getCity();

    void setCity(String city);

    Integer getSchoolId();

    void setSchoolId(Integer schoolId);

    default <T extends SchoolModel> T mergeInto(final T into) {
        if (this.getName() != null) into.setName(this.getName());
        if (this.getStreet() != null) into.setStreet(this.getStreet());
        if (this.getZip() != null) into.setZip(this.getZip());
        if (this.getCity() != null) into.setCity(this.getCity());
        if (this.getSchoolId() != null) into.setSchoolId(this.getSchoolId());
        return into;
    }

}
