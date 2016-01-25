package com.yz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * AnalyzeMan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "analyzeMan", schema = "dbo", catalog = "dchc")
public class AnalyzeMan implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer registrationReason;
	private String degreeEducation;
	private Integer preOccupation;
	private String workdUnit;
	private Integer criminalRecord;
	private Integer criminalRecordTimes;
	private String taoBaoNumber;
	private Integer haveTemporaryResidential;
	private Integer isTemporaryResidentialUseful;
	private Integer residentialZone;
	private String comeToDztime;
	private Integer stayLocation;
	private String address;
	private Integer isStayRegister;
	private Integer isStayRealname;
	private Integer isStayInmate;
	private Integer isNetplayRegister;
	private Integer isNetplayRealname;
	private Integer isCaseHappenNearbyInternetbar;
	private Integer pocketbook;
	private Integer isPropertyCrime;
	private Integer isStreetCrime;
	private Integer isSomeZone;
	private Integer crimeMotive;
	private Integer crimeBigLocation;
	private Integer crimeSmallLocation;
	private Integer crimeTime;
	private Integer transportationTypeCome;
	private Integer transportationTypeGo;
	private Integer fledRange;
	private String crimeMethod;
	private Integer crimeAnalyze;
	private Integer solveWay;
	private Integer isGangCrime;
	private Integer crimeToolSource;
	private Integer foulTo;
	private Integer stolenGoodsWay;
	private Integer mainArrestWay;
	private Integer assistArrestWay;
	private List<Person> persons = new ArrayList<Person>();

	// Constructors

	/** default constructor */
	public AnalyzeMan() {
	}

	/** full constructor */
	public AnalyzeMan(Integer registrationReason, String degreeEducation,
			Integer preOccupation, String workdUnit, Integer criminalRecord,
			Integer criminalRecordTimes, String taoBaoNumber,
			Integer haveTemporaryResidential,
			Integer isTemporaryResidentialUseful, Integer residentialZone,
			String comeToDztime, Integer stayLocation, String address,
			Integer isStayRegister, Integer isStayRealname,
			Integer isStayInmate, Integer isNetplayRegister,
			Integer isNetplayRealname, Integer isCaseHappenNearbyInternetbar,
			Integer pocketbook, Integer isPropertyCrime, Integer isStreetCrime,
			Integer isSomeZone, Integer crimeMotive, Integer crimeBigLocation,
			Integer crimeSmallLocation, Integer crimeTime,
			Integer transportationTypeCome, Integer transportationTypeGo,
			Integer fledRange, String crimeMethod, Integer crimeAnalyze,
			Integer solveWay, Integer isGangCrime, Integer crimeToolSource,
			Integer foulTo, Integer stolenGoodsWay, Integer mainArrestWay,
			Integer assistArrestWay, List<Person> persons) {
		this.registrationReason = registrationReason;
		this.degreeEducation = degreeEducation;
		this.preOccupation = preOccupation;
		this.workdUnit = workdUnit;
		this.criminalRecord = criminalRecord;
		this.criminalRecordTimes = criminalRecordTimes;
		this.taoBaoNumber = taoBaoNumber;
		this.haveTemporaryResidential = haveTemporaryResidential;
		this.isTemporaryResidentialUseful = isTemporaryResidentialUseful;
		this.residentialZone = residentialZone;
		this.comeToDztime = comeToDztime;
		this.stayLocation = stayLocation;
		this.address = address;
		this.isStayRegister = isStayRegister;
		this.isStayRealname = isStayRealname;
		this.isStayInmate = isStayInmate;
		this.isNetplayRegister = isNetplayRegister;
		this.isNetplayRealname = isNetplayRealname;
		this.isCaseHappenNearbyInternetbar = isCaseHappenNearbyInternetbar;
		this.pocketbook = pocketbook;
		this.isPropertyCrime = isPropertyCrime;
		this.isStreetCrime = isStreetCrime;
		this.isSomeZone = isSomeZone;
		this.crimeMotive = crimeMotive;
		this.crimeBigLocation = crimeBigLocation;
		this.crimeSmallLocation = crimeSmallLocation;
		this.crimeTime = crimeTime;
		this.transportationTypeCome = transportationTypeCome;
		this.transportationTypeGo = transportationTypeGo;
		this.fledRange = fledRange;
		this.crimeMethod = crimeMethod;
		this.crimeAnalyze = crimeAnalyze;
		this.solveWay = solveWay;
		this.isGangCrime = isGangCrime;
		this.crimeToolSource = crimeToolSource;
		this.foulTo = foulTo;
		this.stolenGoodsWay = stolenGoodsWay;
		this.mainArrestWay = mainArrestWay;
		this.assistArrestWay = assistArrestWay;
		this.persons = persons;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	@Column(name = "assistArrestWay")
	public Integer getAssistArrestWay() {
		return this.assistArrestWay;
	}

	@Column(name = "comeToDZTime", length = 30)
	public String getComeToDztime() {
		return this.comeToDztime;
	}

	@Column(name = "crimeAnalyze")
	public Integer getCrimeAnalyze() {
		return this.crimeAnalyze;
	}

	@Column(name = "crimeBigLocation")
	public Integer getCrimeBigLocation() {
		return this.crimeBigLocation;
	}

	@Column(name = "crimeMethod", length = 45)
	public String getCrimeMethod() {
		return this.crimeMethod;
	}

	@Column(name = "crimeMotive")
	public Integer getCrimeMotive() {
		return this.crimeMotive;
	}

	@Column(name = "crimeSmallLocation")
	public Integer getCrimeSmallLocation() {
		return this.crimeSmallLocation;
	}

	@Column(name = "crimeTime")
	public Integer getCrimeTime() {
		return this.crimeTime;
	}

	@Column(name = "crimeToolSource")
	public Integer getCrimeToolSource() {
		return this.crimeToolSource;
	}

	@Column(name = "criminalRecord")
	public Integer getCriminalRecord() {
		return this.criminalRecord;
	}

	@Column(name = "criminalRecordTimes")
	public Integer getCriminalRecordTimes() {
		return this.criminalRecordTimes;
	}

	@Column(name = "degreeEducation", length = 20)
	public String getDegreeEducation() {
		return this.degreeEducation;
	}

	@Column(name = "fledRange")
	public Integer getFledRange() {
		return this.fledRange;
	}

	@Column(name = "foulTo")
	public Integer getFoulTo() {
		return this.foulTo;
	}

	@Column(name = "haveTemporaryResidential")
	public Integer getHaveTemporaryResidential() {
		return this.haveTemporaryResidential;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "isCaseHappenNearbyInternetbar")
	public Integer getIsCaseHappenNearbyInternetbar() {
		return this.isCaseHappenNearbyInternetbar;
	}

	@Column(name = "isGangCrime")
	public Integer getIsGangCrime() {
		return this.isGangCrime;
	}

	@Column(name = "isNetplayRealname")
	public Integer getIsNetplayRealname() {
		return this.isNetplayRealname;
	}

	@Column(name = "isNetplayRegister")
	public Integer getIsNetplayRegister() {
		return this.isNetplayRegister;
	}

	@Column(name = "isPropertyCrime")
	public Integer getIsPropertyCrime() {
		return this.isPropertyCrime;
	}

	@Column(name = "isSomeZone")
	public Integer getIsSomeZone() {
		return this.isSomeZone;
	}

	@Column(name = "isStayInmate")
	public Integer getIsStayInmate() {
		return this.isStayInmate;
	}

	@Column(name = "isStayRealname")
	public Integer getIsStayRealname() {
		return this.isStayRealname;
	}

	@Column(name = "isStayRegister")
	public Integer getIsStayRegister() {
		return this.isStayRegister;
	}

	@Column(name = "isStreetCrime")
	public Integer getIsStreetCrime() {
		return this.isStreetCrime;
	}

	@Column(name = "isTemporary ResidentialUseful")
	public Integer getIsTemporaryResidentialUseful() {
		return this.isTemporaryResidentialUseful;
	}

	@Column(name = "mainArrestWay")
	public Integer getMainArrestWay() {
		return this.mainArrestWay;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "analyzeMan")
	public List<Person> getPersons() {
		return this.persons;
	}

	@Column(name = "pocketbook")
	public Integer getPocketbook() {
		return this.pocketbook;
	}

	@Column(name = "preOccupation")
	public Integer getPreOccupation() {
		return this.preOccupation;
	}

	@Column(name = "registrationReason")
	public Integer getRegistrationReason() {
		return this.registrationReason;
	}

	@Column(name = "residentialZone")
	public Integer getResidentialZone() {
		return this.residentialZone;
	}

	@Column(name = "solveWay")
	public Integer getSolveWay() {
		return this.solveWay;
	}

	@Column(name = "stayLocation")
	public Integer getStayLocation() {
		return this.stayLocation;
	}

	@Column(name = "stolenGoodsWay")
	public Integer getStolenGoodsWay() {
		return this.stolenGoodsWay;
	}

	@Column(name = "taoBaoNumber", length = 30)
	public String getTaoBaoNumber() {
		return this.taoBaoNumber;
	}

	@Column(name = "transportationTypeCome")
	public Integer getTransportationTypeCome() {
		return this.transportationTypeCome;
	}

	@Column(name = "transportationTypeGo")
	public Integer getTransportationTypeGo() {
		return this.transportationTypeGo;
	}

	@Column(name = "workdUnit", length = 20)
	public String getWorkdUnit() {
		return this.workdUnit;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAssistArrestWay(Integer assistArrestWay) {
		this.assistArrestWay = assistArrestWay;
	}

	public void setComeToDztime(String comeToDztime) {
		this.comeToDztime = comeToDztime;
	}

	public void setCrimeAnalyze(Integer crimeAnalyze) {
		this.crimeAnalyze = crimeAnalyze;
	}

	public void setCrimeBigLocation(Integer crimeBigLocation) {
		this.crimeBigLocation = crimeBigLocation;
	}

	public void setCrimeMethod(String crimeMethod) {
		this.crimeMethod = crimeMethod;
	}

	public void setCrimeMotive(Integer crimeMotive) {
		this.crimeMotive = crimeMotive;
	}

	public void setCrimeSmallLocation(Integer crimeSmallLocation) {
		this.crimeSmallLocation = crimeSmallLocation;
	}

	public void setCrimeTime(Integer crimeTime) {
		this.crimeTime = crimeTime;
	}

	public void setCrimeToolSource(Integer crimeToolSource) {
		this.crimeToolSource = crimeToolSource;
	}

	public void setCriminalRecord(Integer criminalRecord) {
		this.criminalRecord = criminalRecord;
	}

	public void setCriminalRecordTimes(Integer criminalRecordTimes) {
		this.criminalRecordTimes = criminalRecordTimes;
	}

	public void setDegreeEducation(String degreeEducation) {
		this.degreeEducation = degreeEducation;
	}

	public void setFledRange(Integer fledRange) {
		this.fledRange = fledRange;
	}

	public void setFoulTo(Integer foulTo) {
		this.foulTo = foulTo;
	}

	public void setHaveTemporaryResidential(Integer haveTemporaryResidential) {
		this.haveTemporaryResidential = haveTemporaryResidential;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsCaseHappenNearbyInternetbar(
			Integer isCaseHappenNearbyInternetbar) {
		this.isCaseHappenNearbyInternetbar = isCaseHappenNearbyInternetbar;
	}

	public void setIsGangCrime(Integer isGangCrime) {
		this.isGangCrime = isGangCrime;
	}

	public void setIsNetplayRealname(Integer isNetplayRealname) {
		this.isNetplayRealname = isNetplayRealname;
	}

	public void setIsNetplayRegister(Integer isNetplayRegister) {
		this.isNetplayRegister = isNetplayRegister;
	}

	public void setIsPropertyCrime(Integer isPropertyCrime) {
		this.isPropertyCrime = isPropertyCrime;
	}

	public void setIsSomeZone(Integer isSomeZone) {
		this.isSomeZone = isSomeZone;
	}

	public void setIsStayInmate(Integer isStayInmate) {
		this.isStayInmate = isStayInmate;
	}

	public void setIsStayRealname(Integer isStayRealname) {
		this.isStayRealname = isStayRealname;
	}

	public void setIsStayRegister(Integer isStayRegister) {
		this.isStayRegister = isStayRegister;
	}

	public void setIsStreetCrime(Integer isStreetCrime) {
		this.isStreetCrime = isStreetCrime;
	}

	public void setIsTemporaryResidentialUseful(
			Integer isTemporaryResidentialUseful) {
		this.isTemporaryResidentialUseful = isTemporaryResidentialUseful;
	}

	public void setMainArrestWay(Integer mainArrestWay) {
		this.mainArrestWay = mainArrestWay;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void setPocketbook(Integer pocketbook) {
		this.pocketbook = pocketbook;
	}

	public void setPreOccupation(Integer preOccupation) {
		this.preOccupation = preOccupation;
	}

	public void setRegistrationReason(Integer registrationReason) {
		this.registrationReason = registrationReason;
	}

	public void setResidentialZone(Integer residentialZone) {
		this.residentialZone = residentialZone;
	}

	public void setSolveWay(Integer solveWay) {
		this.solveWay = solveWay;
	}

	public void setStayLocation(Integer stayLocation) {
		this.stayLocation = stayLocation;
	}

	public void setStolenGoodsWay(Integer stolenGoodsWay) {
		this.stolenGoodsWay = stolenGoodsWay;
	}

	public void setTaoBaoNumber(String taoBaoNumber) {
		this.taoBaoNumber = taoBaoNumber;
	}

	public void setTransportationTypeCome(Integer transportationTypeCome) {
		this.transportationTypeCome = transportationTypeCome;
	}

	public void setTransportationTypeGo(Integer transportationTypeGo) {
		this.transportationTypeGo = transportationTypeGo;
	}

	public void setWorkdUnit(String workdUnit) {
		this.workdUnit = workdUnit;
	}

}