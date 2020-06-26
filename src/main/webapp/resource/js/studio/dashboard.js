function updateStatusNo(projectNo) {
  const url = cp + "/studio/project/" + projectNo + "/submit";
  location.href = url;
}
