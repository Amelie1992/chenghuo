const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

const diffTime = (startDate, endDate)=> {
  var diff = endDate.getTime() - startDate.getTime();//时间差的毫秒数  

  //计算出相差天数  
  var days = Math.floor(diff / (24 * 3600 * 1000));

  //计算出小时数  
  var leave1 = diff % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数  
  var hours = Math.floor(leave1 / (3600 * 1000));
  //计算相差分钟数  
  var leave2 = leave1 % (3600 * 1000);        //计算小时数后剩余的毫秒数  
  var minutes = Math.floor(leave2 / (60 * 1000));

  //计算相差秒数  
  var leave3 = leave2 % (60 * 1000);      //计算分钟数后剩余的毫秒数  
  var seconds = Math.round(leave3 / 1000);

  var returnStr = seconds + "秒";
  if (minutes > 0) {
    returnStr = minutes + "分" + returnStr;
  }
  if (hours > 0) {
    returnStr = hours + "小时" + returnStr;
  }
  if (days > 0) {
    returnStr = days + "天" + returnStr;
  }
  return returnStr;
}  

module.exports = {
  formatTime: formatTime,
  diffTime : diffTime
}
