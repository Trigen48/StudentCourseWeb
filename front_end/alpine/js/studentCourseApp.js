var ServerURL = "http://localhost:8080";

var studentCourseData = {
  init() {},
  courses: [],
  students: [],
  studentCourses: [],
  courseSelect: [],

  studentInput: { studentNumber: 0, firstname: "", surname: "" },
  courseInput: { courseCode: "", courseDescription: "" },
  studentCourseInput: { studentNumber: "", courseCode: "", grade: "" },

  showStudentCourse: false,
  showStudentAdd: false,
  showStudentEdit: false,

  showCourseAdd: false,
  showCourseEdit: false,

  loading_visible: false,
  showCoursePanel: false,
  showStudentPanel: true,
  showUploadPanel: false,
  addStudent() {},

  ///////// Student Code
  loadAllStudents() {
    this.students = [];
    axios
      .get(`${ServerURL}/student/`)
      .then((result) => result.data)
      .then((result) => (this.students = result))
      .catch((error) => alert(error));
  },

  addStudent() {
    this.studentInput = { studentNumber: 0, firstname: "", surname: "" };
    this.showStudentAdd = true;
  },

  editStudent(studentInfo) {
    this.studentInput = studentInfo;

    this.showStudentEdit = true;
  },

  saveStudentAdd() {
    axios
      .post(`${ServerURL}/student/`, this.studentInput)
      .then((result) => result.data)
      .then((result) => this.addStudentNotify(result))
      .catch((error) => alert(error));
  },

  addStudentNotify(result) {
    alert(result.message);
    if (result.status == 1) {
      this.hideStudentAddEdit();
      this.loadAllStudents();
    }
  },

  saveStudentEdit() {
    axios
      .put(
        `${ServerURL}/student/${this.studentInput.studentNumber}/`,
        this.studentInput
      )
      .then((result) => result.data)
      .then((result) => this.editStudentNotify(result))
      .catch((error) => alert(error));
  },

  editStudentNotify(result) {
    alert(result.message);
    if (result.status == 1) {
      this.hideStudentAddEdit();
      this.loadAllStudents();
    }
  },

  deleteStudent(studentNumber) {
    axios
      .delete(`${ServerURL}/student/${studentNumber}/`)
      .then((result) => result.data)
      .then((result) => this.deleteStudentNotify(result))
      .catch((error) => alert(error));
  },

  deleteStudentNotify(deleteResult) {
    alert(deleteResult.message);

    if (deleteResult.status == 1) {
      this.loadAllStudents();
    }
  },
  hideStudentAddEdit() {
    this.showStudentEdit = false;
    this.showStudentAdd = false;
  },

  displayStudentCourse(student) {
    this.studentInput = student;
    this.showStudentCourse = true;
    this.loadStudentCourse();
  },

  ///////////////
  // Course Code

  loadAllCourses() {
    this.courses = [];
    axios
      .get(`${ServerURL}/course/`)
      .then((result) => result.data)
      .then((result) => (this.courses = result))
      .catch((error) => alert(error));
  },

  addCourse() {
    this.courseInput = { courseCode: "", courseDescription: "" };
    this.showCourseAdd = true;
  },

  editCourse(courseInfo) {
    this.courseInput = courseInfo;

    this.showCourseEdit = true;
  },

  saveCourseAdd() {
    axios
      .post(`${ServerURL}/course/`, this.courseInput)
      .then((result) => result.data)
      .then((result) => this.addCourseNotify(result))
      .catch((error) => alert(error));
  },

  addCourseNotify(result) {
    alert(result.message);
    if (result.status == 1) {
      this.hideCourseAddEdit();
      this.loadAllCourses();
    }
  },

  saveCourseEdit() {
    axios
      .put(
        `${ServerURL}/course/${this.courseInput.courseCode}/`,
        this.courseInput
      )
      .then((result) => result.data)
      .then((result) => this.editCourseNotify(result))
      .catch((error) => alert(error));
  },

  editCourseNotify(result) {
    alert(result.message);
    if (result.status == 1) {
      this.hideCourseAddEdit();
      this.loadAllCourses();
    }
  },

  deleteCourse(courseCode) {
    axios
      .delete(`${ServerURL}/course/${courseCode}/`)
      .then((result) => result.data)
      .then((result) => this.deleteCourseNotify(result))
      .catch((error) => alert(error));
  },

  deleteCourseNotify(deleteResult) {
    alert(deleteResult.message);

    if (deleteResult.status == 1) {
      this.loadAllCourses();
    }
  },
  hideCourseAddEdit() {
    this.showCourseEdit = false;
    this.showCourseAdd = false;
  },

  displayCourseCourse(course) {
    this.courseInput = course;
    this.showCourseCourse = true;
    this.loadCourseCourse();
  },

  ////////// student Course

  loadStudentCourse() {
    this.studentCourses = [];
    axios
      .get(`${ServerURL}/student/course/${this.studentInput.studentNumber}/`)
      .then((result) => result.data)
      .then((result) => (this.studentCourses = result))
      .catch((error) => alert(error));
  },

  displayStudents() {
    this.studentCourses = [];
    this.showStudentCourse = false;
    this.loadAllStudents();
  },

  getInputStudent() {
    return `${this.studentInput.studentNumber} - ${this.studentInput.firstname}, ${this.studentInput.surname}`;
  },

  clearLists() {
    this.students = [];
    this.studentCourses = [];
    this.courses = [];

    this.showStudentCourse = false;
    this.showStudentAdd = false;
    this.showStudentEdit = false;

    this.showCourseAdd = false;
    this.showCourseEdit = false;

    this.loading_visible = false;
    this.showCoursePanel = false;
    this.showStudentPanel = false;
    this.showUploadPanel = false;
  },

  displayStudentPanel() {
    this.clearLists();
    this.showStudentPanel = true;
    this.loadAllStudents();
  },

  displayCoursePanel() {
    this.clearLists();
    this.showCoursePanel = true;
    this.loadAllCourses();
  },

  displayUploadPanel() {
    this.clearLists();
    this.showUploadPanel = true;
  },
};
