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
 * AnalyzeMan entity.侵财分析人员
 * 
 * @author lq
 */
@Entity
@Table(name = "analyzeMan", schema = "dbo", catalog = "dchc")
public class AnalyzeMan implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer registrationReason;//登记缘由（1刑事拘留2刑事传唤3留置盘问4取保候查）
	private String lawcaseReason;//涉嫌案由
	private String nickname;//别名
	private String degreeEducation;//文化程度
	private String nationality;//名族
	private Integer preOccupation;//入所前职业( 1固定职业2临时工3无业)
	private String workdUnit;//工作单位
	private Integer criminalRecord;//前科(1盗窃 2抢劫 3抢夺 4其他)
	private Integer criminalRecordTimes;//前科次数
	private String taoBaoNumber;//淘宝号
	private Integer haveTemporaryResidential;//暂住证有无（ 1有2无）
	private Integer isTemporaryResidentialUseful;//暂住证是否失效(1有效2无效)
	private Integer residentialZone;//居住区域（ 1省外2市外3市内区外4本区5本单位辖区）
	private String comeToDztime;//来德州时间
	private Integer stayLocation;//	居住场所（ 1租房2旅馆3网吧4寄宿5浴室6车船码头7公园）
	private String address;//	地址
	private Integer isStayRegister;//住宿旅店有无登记
	private Integer isStayRealname;//住宿旅店有无实名认证
	private Integer isStayInmate;//	住宿旅店有无同住
	private Integer isNetplayRegister;//上网有无登记
	private Integer isNetplayRealname;//上网有无实名认证
	private Integer isCaseHappenNearbyInternetbar;//上网是否在案发地附近
	private Integer pocketbook;//经济来源（ 1务工2经商3亲朋资助4无）
	private Integer isPropertyCrime;//是否侵财（ 1是2否）
	private Integer isStreetCrime;//街面犯罪（ 1是2否）
	private Integer isSomeZone;//作案地与居住地是否为同地（ 1是2否）
	private Integer crimeMotive;//作案动机（ 1经济胁迫2挥霍享乐3寻找刺激4报复纠纷5同伙相约6教唆胁迫）
	private Integer crimeBigLocation;//作案地点大范围（  1城区2农村3城郊4公路5野外 ）
	private Integer crimeSmallLocation;//	作案地点小范围（ 1大型商场2小型店铺3广场车站4居民住宅5偏僻地带6机关单位7长砖企业8娱乐场所9农宅租房)
	private Integer crimeTime;//作案时间（ 1上午作案2下午作案3前半夜作案4后半夜作案 ）
	private Integer transportationTypeCome;//选择交通方式来（ 1徒步2公交车3私家车4自行车5地铁）
	private Integer transportationTypeGo;//	选择交通方式去( 1徒步2公交车3私家车4自行车5地铁)
	private Integer fledRange;//流窜范围（ 1本地2跨社区3跨街道4跨区县5跨市6跨省）
	private String crimeMethod;//作案手段方式
	private Integer crimeAnalyze;//	作案分析 1夜盗2白闯3盗窃电动车4其他盗窃5抢劫6诈骗）
	private Integer solveWay;//破案途径（ 1技侦2网监3视频4技术5信息6巡逻设卡7审查8情报9投案自首10银行查询）
	private Integer isGangCrime;//是否结伙作案（ 1是2否）
	private Integer gangGx;//结伙成员关系
	private Integer gangNumber;//成员人数
	private Integer crimeToolSource;//作案工具来源（ 1网购2商购3自制4他人给予）
	private Integer foulTo;//脏物去向（ 1本地2跨社区3跨街道4跨区县5跨市6跨省）
	private Integer stolenGoodsWay;//	销赃途径( 1卖2典当3散卖4自用5转送)
	private Integer mainArrestWay;//	主要抓捕途径（ 1背包机2技侦3布控4网上追逃5信息研判6巡逻设卡7投案自首8其他）
	private Integer assistArrestWay;//辅助抓捕途径（ 1背包机2技侦3布控4网上追逃5信息研判6巡逻设卡7投案自首8其他）
	private List<Person> persons = new ArrayList<Person>();

	// Constructors

	/** default constructor */
	public AnalyzeMan() {
	}

	/** full constructor */
	public AnalyzeMan(Integer registrationReason, String nickname,String lawcaseReason,String degreeEducation,String nationality,
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
			Integer solveWay, Integer isGangCrime,Integer gangGx,Integer gangNumber, Integer crimeToolSource,
			Integer foulTo, Integer stolenGoodsWay, Integer mainArrestWay,
			Integer assistArrestWay, List<Person> persons) {
		this.registrationReason = registrationReason;
		this.lawcaseReason = lawcaseReason;
		this.nickname = nickname;
		this.nationality = nationality;
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
		this.gangGx = gangGx;
		this.gangNumber = gangNumber;
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

	@Column(name = "gangGx")
	public Integer getGangGx() {
		return gangGx;
	}

	@Column(name = "gangNumber")
	public Integer getGangNumber() {
		return gangNumber;
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

	@Column(name = "isTemporaryResidentialUseful")
	public Integer getIsTemporaryResidentialUseful() {
		return this.isTemporaryResidentialUseful;
	}

	@Column(name = "lawcaseReason",length = 50)
	public String getLawcaseReason() {
		return lawcaseReason;
	}

	@Column(name = "mainArrestWay")
	public Integer getMainArrestWay() {
		return this.mainArrestWay;
	}

	@Column(name = "nationality")
	public String getNationality() {
		return nationality;
	}

	@Column(name = "nickname",length = 50)
	public String getNickname() {
		return nickname;
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

	public void setGangGx(Integer gangGx) {
		this.gangGx = gangGx;
	}

	public void setGangNumber(Integer gangNumber) {
		this.gangNumber = gangNumber;
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

	public void setLawcaseReason(String lawcaseReason) {
		this.lawcaseReason = lawcaseReason;
	}

	public void setMainArrestWay(Integer mainArrestWay) {
		this.mainArrestWay = mainArrestWay;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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