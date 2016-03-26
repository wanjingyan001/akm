package cn.zsmy.akm.doctor.bean;

import java.util.List;

/**
 * 我的病人列表
 * Created by Administrator on 2015/12/23.
 */
public class MyPatients {


    /**
     * code : SUCC
     * message : 医生的病人返回成功
     * data : [{"id":"ab4d1454-c4cc-4f81-951b-9574ae438c44","userId":"9415d58d-7857-40e3-805d-43f8545543af","doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":66,"cases":{"id":"03c03dfb-b7a0-4751-bc21-e198e7b2f6f0","patientId":"d196fd2e-e2d1-4431-94fa-499de2700f32","content":"电话问诊测试咯摸咯摩羯","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1453085124000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453089311000,"status":"1","zType":"0","flag":"2","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":3,"lastChatType":"doctor","lastChatTime":1453089311000,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"d196fd2e-e2d1-4431-94fa-499de2700f32","name":"急救","birthday":1105804800000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"2cf90839-d45d-4e46-b5ac-240abd7b2e41","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"6c5c835d-8551-434a-ab11-b914d5289f40","patientId":"2ff28022-2254-4adf-aa31-3aee854fe6bc","content":"20160112聊天测试5怎样啃聊天","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452590525000,"modifier":"9415d58d-7857-40e3-805d-43f8545543af","modifyTime":1452927410000,"status":"2","zType":"1","flag":null,"offSet":null,"evaluate":"","evaluateScore":0,"evaluateTime":"2016-01-13","caseLength":0,"lastChatType":"akeman","lastChatTime":1452591882000,"veriftyTime":1452657415000,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"2ff28022-2254-4adf-aa31-3aee854fe6bc","name":"就是就是","birthday":-61977600000,"gender":"女","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"6e51fc26-d432-4172-bbc0-499b50b33964","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"92212604-c480-4b9c-b611-79772674c7bd","patientId":"2ff28022-2254-4adf-aa31-3aee854fe6bc","content":"聊天测试让他雨iu太让人土语","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452910986000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1452919687000,"status":"2","zType":"1","flag":"1","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":"doctor","lastChatTime":1452919686000,"veriftyTime":1452919525000,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"2ff28022-2254-4adf-aa31-3aee854fe6bc","name":"就是就是","birthday":-61977600000,"gender":"女","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"ddbeb404-180d-4620-ad0b-e12d52c0763a","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"6c5c835d-8551-434a-ab11-b914d5289f40","patientId":"2ff28022-2254-4adf-aa31-3aee854fe6bc","content":"20160112聊天测试5怎样啃聊天","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452590525000,"modifier":"9415d58d-7857-40e3-805d-43f8545543af","modifyTime":1452927410000,"status":"2","zType":"1","flag":null,"offSet":null,"evaluate":"","evaluateScore":0,"evaluateTime":"2016-01-13","caseLength":0,"lastChatType":"akeman","lastChatTime":1452591882000,"veriftyTime":1452657415000,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"2ff28022-2254-4adf-aa31-3aee854fe6bc","name":"就是就是","birthday":-61977600000,"gender":"女","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"8664cdd0-d0cc-40c9-8fb8-809c1a33cb2c","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"6cafa88c-221c-4b9b-ad24-5577fb0ba40f","patientId":"d196fd2e-e2d1-4431-94fa-499de2700f32","content":"201611610061111","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452909992000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1452910411000,"status":"2","zType":"1","flag":"1","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":"akeman","lastChatTime":1452910386000,"veriftyTime":1452910411000,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"d196fd2e-e2d1-4431-94fa-499de2700f32","name":"急救","birthday":1105804800000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"a7da126f-c2cc-4ed1-8f2b-2d7630ce4ae8","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"8a701c36-13de-4010-a9f7-f1d98e14f76b","patientId":"d196fd2e-e2d1-4431-94fa-499de2700f32","content":"2016011655366633","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452909294000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1452922769000,"status":"1","zType":"1","flag":"1","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":"doctor","lastChatTime":1452922768000,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"d196fd2e-e2d1-4431-94fa-499de2700f32","name":"急救","birthday":1105804800000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"f467a99b-0232-46f7-81ae-b941cd3a9bb8","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"28f02f3e-789c-4b32-b184-afc525b15c7d","patientId":"90533d28-24d7-4a4a-b4c0-adcf6bb6cd99","content":"DJ记得记得健康大口大口大口大口","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452838345000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1452862627000,"status":"2","zType":"1","flag":"1","offSet":null,"evaluate":null,"evaluateScore":2,"evaluateTime":null,"caseLength":0,"lastChatType":null,"lastChatTime":null,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"90533d28-24d7-4a4a-b4c0-adcf6bb6cd99","name":"4543432","birthday":1168790400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"a4c8fdfd-6a73-47b0-a903-b7f2c3ba42eb","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"28f02f3e-789c-4b32-b184-afc525b15c7d","patientId":"90533d28-24d7-4a4a-b4c0-adcf6bb6cd99","content":"DJ记得记得健康大口大口大口大口","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452838345000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1452862627000,"status":"2","zType":"1","flag":"1","offSet":null,"evaluate":null,"evaluateScore":2,"evaluateTime":null,"caseLength":0,"lastChatType":null,"lastChatTime":null,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"90533d28-24d7-4a4a-b4c0-adcf6bb6cd99","name":"4543432","birthday":1168790400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"9e1094ca-9e87-47e0-83a0-a2db4378cac5","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"3c55518d-f132-4f4a-a2dd-4f84f192937c","patientId":"90533d28-24d7-4a4a-b4c0-adcf6bb6cd99","content":"对弟弟哦哦哦哦哦咳咳咳咳","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452837369000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1452862495000,"status":"2","zType":"1","flag":"2","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":null,"lastChatTime":null,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"90533d28-24d7-4a4a-b4c0-adcf6bb6cd99","name":"4543432","birthday":1168790400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"58d9d3d4-d360-4343-9e83-1c89155818cf","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"cb622aed-1709-45ee-a09a-33d2db0de6e7","patientId":"90533d28-24d7-4a4a-b4c0-adcf6bb6cd99","content":"重复句就感觉$就_恐怖","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452838604000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1452862431000,"status":"2","zType":"1","flag":"2","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":null,"lastChatTime":null,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"90533d28-24d7-4a4a-b4c0-adcf6bb6cd99","name":"4543432","birthday":1168790400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"5f4817bb-96e8-4165-9300-a1f45498d747","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"3dbf8df9-a241-42ed-bb1e-0a6c05949874","patientId":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","content":"妇科v看v旧款不错就好-","memo":"null","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1452838910000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453001259000,"status":"1","zType":"1","flag":"2","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":"doctor","lastChatTime":1452863300000,"veriftyTime":1453001259000,"triageTime":null,"inquirer":{"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","name":"我本人","birthday":506102400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"0d215812-27d9-4082-9d5e-3b03a67343a8","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"d3c8973f-ef52-45dd-b0e4-64ec4ddab977","patientId":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","content":"欢喜就好就到家开导开导看的开","memo":"null","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1452838864000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453089517000,"status":"1","zType":"1","flag":"2","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":"akeman","lastChatTime":1453088701000,"veriftyTime":1453089517000,"triageTime":null,"inquirer":{"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"1eb1db67-dc28-4ae0-b854-9d0140adc8cb","name":"我本人","birthday":506102400000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"cec0953c-97c1-4435-be93-790e8f3691f6","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"31e867d7-567b-42de-9f28-af407abbf8c7","patientId":"6abd5a0e-d9d6-4d6d-8a8f-e52022ab011d","content":"2016-1-14测试","memo":"null","creator":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","createTime":1452747905000,"modifier":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","modifyTime":1452838281000,"status":"2","zType":"0","flag":null,"offSet":null,"evaluate":"","evaluateScore":35,"evaluateTime":"2016-01-15","caseLength":0,"lastChatType":null,"lastChatTime":null,"veriftyTime":null,"triageTime":1452760389000,"inquirer":{"id":"d9489b4f-043b-4fbc-8ae2-e49b460e528c","name":"1351***8","nickname":"隊長","avatar":"upload/avatar/2016/1/18/14530891835042092","phone":"13511007508"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"6abd5a0e-d9d6-4d6d-8a8f-e52022ab011d","name":"记不住","birthday":569088000000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"851810ea-c635-4ccf-956d-1e3d8ac2c3a0","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":1,"cases":{"id":"6c5c835d-8551-434a-ab11-b914d5289f40","patientId":"2ff28022-2254-4adf-aa31-3aee854fe6bc","content":"20160112聊天测试5怎样啃聊天","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452590525000,"modifier":"9415d58d-7857-40e3-805d-43f8545543af","modifyTime":1452927410000,"status":"2","zType":"1","flag":null,"offSet":null,"evaluate":"","evaluateScore":0,"evaluateTime":"2016-01-13","caseLength":0,"lastChatType":"akeman","lastChatTime":1452591882000,"veriftyTime":1452657415000,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"2ff28022-2254-4adf-aa31-3aee854fe6bc","name":"就是就是","birthday":-61977600000,"gender":"女","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}},{"id":"7c5cee5b-270e-46ab-bf37-c19fed2fa76b","userId":null,"doctorId":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","num":7,"cases":{"id":"52054d85-d755-4745-b7b9-5f1b572702ee","patientId":"2ff28022-2254-4adf-aa31-3aee854fe6bc","content":"从普通问诊开始测试的，17:20","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1452675751000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1452685485000,"status":"2","zType":"0","flag":null,"offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":0,"lastChatType":"doctor","lastChatTime":1452677876000,"veriftyTime":1452685485000,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"2ff28022-2254-4adf-aa31-3aee854fe6bc","name":"就是就是","birthday":-61977600000,"gender":"女","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}}]
     */

    private String code;
    private String message;
    /**
     * id : ab4d1454-c4cc-4f81-951b-9574ae438c44
     * userId : 9415d58d-7857-40e3-805d-43f8545543af
     * doctorId : bcf0735f-c335-4897-8ea3-ed14b25f23b0
     * num : 66
     * cases : {"id":"03c03dfb-b7a0-4751-bc21-e198e7b2f6f0","patientId":"d196fd2e-e2d1-4431-94fa-499de2700f32","content":"电话问诊测试咯摸咯摩羯","memo":"null","creator":"9415d58d-7857-40e3-805d-43f8545543af","createTime":1453085124000,"modifier":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","modifyTime":1453089311000,"status":"1","zType":"0","flag":"2","offSet":null,"evaluate":null,"evaluateScore":0,"evaluateTime":null,"caseLength":3,"lastChatType":"doctor","lastChatTime":1453089311000,"veriftyTime":null,"triageTime":null,"inquirer":{"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"},"doctor":{"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"},"patient":{"id":"d196fd2e-e2d1-4431-94fa-499de2700f32","name":"急救","birthday":1105804800000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}}
     */

    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String userId;
        private String doctorId;
        private int num;
        /**
         * id : 03c03dfb-b7a0-4751-bc21-e198e7b2f6f0
         * patientId : d196fd2e-e2d1-4431-94fa-499de2700f32
         * content : 电话问诊测试咯摸咯摩羯
         * memo : null
         * creator : 9415d58d-7857-40e3-805d-43f8545543af
         * createTime : 1453085124000
         * modifier : bcf0735f-c335-4897-8ea3-ed14b25f23b0
         * modifyTime : 1453089311000
         * status : 1
         * zType : 0
         * flag : 2
         * offSet : null
         * evaluate : null
         * evaluateScore : 0
         * evaluateTime : null
         * caseLength : 3
         * lastChatType : doctor
         * lastChatTime : 1453089311000
         * veriftyTime : null
         * triageTime : null
         * inquirer : {"id":"9415d58d-7857-40e3-805d-43f8545543af","name":"1376***2","nickname":"问诊人昵称2","avatar":"upload/avatar/2016/1/18/14530840907651373","phone":"13761577422"}
         * doctor : {"id":"bcf0735f-c335-4897-8ea3-ed14b25f23b0","name":"sanz","professionalTitle":"住院医师","avatar":"upload/casePic/2016/1/14/14527739227084854","phone":"13585906920","hos":null,"hospital":"上海市第七人民医院","deptId":"7978df44-9fe6-421b-aba0-f6beac258246"}
         * patient : {"id":"d196fd2e-e2d1-4431-94fa-499de2700f32","name":"急救","birthday":1105804800000,"gender":"男","caseNum":0,"offsetX":null,"offsetY":null,"offset":null}
         */

        private CasesEntity cases;

        public void setId(String id) {
            this.id = id;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setCases(CasesEntity cases) {
            this.cases = cases;
        }

        public String getId() {
            return id;
        }

        public String getUserId() {
            return userId;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public int getNum() {
            return num;
        }

        public CasesEntity getCases() {
            return cases;
        }

        public static class CasesEntity {
            private String id;
            private String patientId;
            private String content;
            private String memo;
            private String creator;
            private long createTime;
            private String modifier;
            private long modifyTime;
            private String status;
            private String zType;
            private String flag;
            private Object offSet;
            private Object evaluate;
            private int evaluateScore;
            private Object evaluateTime;
            private int caseLength;
            private String lastChatType;
            private long lastChatTime;
            private String lastChatContent;
            private Object veriftyTime;
            private Object triageTime;
            /**
             * id : 9415d58d-7857-40e3-805d-43f8545543af
             * name : 1376***2
             * nickname : 问诊人昵称2
             * avatar : upload/avatar/2016/1/18/14530840907651373
             * phone : 13761577422
             */

            private InquirerEntity inquirer;
            /**
             * id : bcf0735f-c335-4897-8ea3-ed14b25f23b0
             * name : sanz
             * professionalTitle : 住院医师
             * avatar : upload/casePic/2016/1/14/14527739227084854
             * phone : 13585906920
             * hos : null
             * hospital : 上海市第七人民医院
             * deptId : 7978df44-9fe6-421b-aba0-f6beac258246
             */

            private DoctorEntity doctor;
            /**
             * id : d196fd2e-e2d1-4431-94fa-499de2700f32
             * name : 急救
             * birthday : 1105804800000
             * gender : 男
             * caseNum : 0
             * offsetX : null
             * offsetY : null
             * offset : null
             */

            private PatientEntity patient;

            public void setId(String id) {
                this.id = id;
            }

            public void setPatientId(String patientId) {
                this.patientId = patientId;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setModifier(String modifier) {
                this.modifier = modifier;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setZType(String zType) {
                this.zType = zType;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public void setOffSet(Object offSet) {
                this.offSet = offSet;
            }

            public void setEvaluate(Object evaluate) {
                this.evaluate = evaluate;
            }

            public void setEvaluateScore(int evaluateScore) {
                this.evaluateScore = evaluateScore;
            }

            public void setEvaluateTime(Object evaluateTime) {
                this.evaluateTime = evaluateTime;
            }

            public void setCaseLength(int caseLength) {
                this.caseLength = caseLength;
            }

            public void setLastChatType(String lastChatType) {
                this.lastChatType = lastChatType;
            }

            public void setLastChatTime(long lastChatTime) {
                this.lastChatTime = lastChatTime;
            }

            public void setVeriftyTime(Object veriftyTime) {
                this.veriftyTime = veriftyTime;
            }

            public void setTriageTime(Object triageTime) {
                this.triageTime = triageTime;
            }

            public void setInquirer(InquirerEntity inquirer) {
                this.inquirer = inquirer;
            }

            public void setDoctor(DoctorEntity doctor) {
                this.doctor = doctor;
            }

            public void setPatient(PatientEntity patient) {
                this.patient = patient;
            }

            public String getId() {
                return id;
            }

            public String getPatientId() {
                return patientId;
            }

            public String getContent() {
                return content;
            }

            public String getMemo() {
                return memo;
            }

            public String getCreator() {
                return creator;
            }

            public long getCreateTime() {
                return createTime;
            }

            public String getModifier() {
                return modifier;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public String getStatus() {
                return status;
            }

            public String getZType() {
                return zType;
            }

            public String getFlag() {
                return flag;
            }

            public Object getOffSet() {
                return offSet;
            }

            public Object getEvaluate() {
                return evaluate;
            }

            public int getEvaluateScore() {
                return evaluateScore;
            }

            public Object getEvaluateTime() {
                return evaluateTime;
            }

            public int getCaseLength() {
                return caseLength;
            }

            public String getLastChatType() {
                return lastChatType;
            }

            public long getLastChatTime() {
                return lastChatTime;
            }

            public Object getVeriftyTime() {
                return veriftyTime;
            }

            public Object getTriageTime() {
                return triageTime;
            }

            public InquirerEntity getInquirer() {
                return inquirer;
            }

            public DoctorEntity getDoctor() {
                return doctor;
            }

            public PatientEntity getPatient() {
                return patient;
            }

            public String getLastChatContent() {
                return lastChatContent;
            }

            public void setLastChatContent(String lastChatContent) {
                this.lastChatContent = lastChatContent;
            }

            public static class InquirerEntity {
                private String id;
                private String name;
                private String nickname;
                private String avatar;
                private String phone;

                public void setId(String id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public String getNickname() {
                    return nickname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public String getPhone() {
                    return phone;
                }
            }

            public static class DoctorEntity {
                private String id;
                private String name;
                private String professionalTitle;
                private String avatar;
                private String phone;
                private Object hos;
                private String hospital;
                private String deptId;

                public void setId(String id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setProfessionalTitle(String professionalTitle) {
                    this.professionalTitle = professionalTitle;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public void setHos(Object hos) {
                    this.hos = hos;
                }

                public void setHospital(String hospital) {
                    this.hospital = hospital;
                }

                public void setDeptId(String deptId) {
                    this.deptId = deptId;
                }

                public String getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public String getProfessionalTitle() {
                    return professionalTitle;
                }

                public String getAvatar() {
                    return avatar;
                }

                public String getPhone() {
                    return phone;
                }

                public Object getHos() {
                    return hos;
                }

                public String getHospital() {
                    return hospital;
                }

                public String getDeptId() {
                    return deptId;
                }
            }

            public static class PatientEntity {
                private String id;
                private String name;
                private long birthday;
                private String gender;
                private int caseNum;
                private Object offsetX;
                private Object offsetY;
                private Object offset;

                public void setId(String id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setBirthday(long birthday) {
                    this.birthday = birthday;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public void setCaseNum(int caseNum) {
                    this.caseNum = caseNum;
                }

                public void setOffsetX(Object offsetX) {
                    this.offsetX = offsetX;
                }

                public void setOffsetY(Object offsetY) {
                    this.offsetY = offsetY;
                }

                public void setOffset(Object offset) {
                    this.offset = offset;
                }

                public String getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public long getBirthday() {
                    return birthday;
                }

                public String getGender() {
                    return gender;
                }

                public int getCaseNum() {
                    return caseNum;
                }

                public Object getOffsetX() {
                    return offsetX;
                }

                public Object getOffsetY() {
                    return offsetY;
                }

                public Object getOffset() {
                    return offset;
                }
            }
        }
    }
}
