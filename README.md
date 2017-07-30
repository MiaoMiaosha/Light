LightProject项目

后端采用Maven+Spring+SpringMVC+Mybatis，
结合微信提供的API进行开发，具体API介绍可查看微信公众平台：
https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1445241432
图片等资料的存储使用了七牛云
https://www.qiniu.com/

LightProject为后端JAVA程序
light-generator为Mybatis逆向工程
mobile为微信手机前端代码

该项目分为三大模块：

一、用户发布信息及审核

二、工程项目进度管理

三、员工记账管理功能

LightProject市场平台接口文档
API接口说明
接口规范：
返回的数据格式统一为json格式 
2.返回的数据格式一般为
{
    "status": {
        "code": 0,
        "msg": "ok",
        "timestamp": 1476946284
    },
    "data": {
        "XXX":"XXX"
    }
}
含有分页的消息
{
    "status": {
        "code": 0,
        "msg": "ok",
        "tmestamp": 1476946284,
        "curPage": 1,
        "total": 100
    },
   "data": {
   "XXX":"XXX"
    }
}

调用需要登录验证接口均需传入参数
目前需要登录验证的接口，都以/admin为前缀
{username:1,password:"xxxxxxxxxx",inpcode:"xxxx"}
暂定域名为 http://xxx.com/LightProject


1.案例相关接口
1.1 获取案例列表
{
url:
"/case/list",
methods:
"GET",
params:{
（可选）
“userId”:1,
“caseName”:”案例名称模糊查询”,
“level”:1,//案例星级
“startTime”:1487209331,
“endTime”:1487209331,
“state”:1,//0-未发布，1-使用中，2-已关闭
“page”:1,
“rows”:4
},
return:{
{
    "status": {
        "total": 3,
        "timestamp": 1487209702,
        "curPage": 2,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "cid": 1,
            "userId": 1,
            "imgUrl": "/head.jpg",
            "caseName": "案例名称1",
            "level": 1,
            "description": "这是一个挺好的案例",
            "createTime": 1487209331,
            "area": "100平米",
            "state": 1
        },
        {
            "cid": 3,
            "userId": 1,
            "imgUrl": "/test.png",
            "caseName": "案例名称2",
            "level": 2,
            "description": "这也是一个很好的案例",
            "createTime": 1487209340,
            "area": "50平米",
            "state": 1
        }
    ]
}
}
1.2发布案例
{
url:
"/case/add",
methods:
"POST",
params:{
（必填）
userId : 1,
（可选）
imgUrl: /kaka, 接口返回的key，多张用#分隔
caseName: 案例名称
description: 案例相关描述
area: 面积
},
return:{
{
    "status": {
        "timestamp": 1487212852,
        "code": 200,
        "msg": "插入成功"
    },
    "data": {
        "applyId": 1,//申请id
        "caseId": 6 //案例id
    }
}

}

1.3修改案例详情
{
//管理员才拥有权限
url:
"/admin/case/edit",
methods:
"POST",
params:{
（必填）
 cid : 5
（可选）
caseName ： 市场名称，string
level ： 等级，integer
descriotin : 描述，String
area : 面积，string
state : 状态，0-未发布，1-使用中，2-已关闭
},
return:{

{"status":{"timestamp":1487279568,"code":200,"msg":"更新信息成功"}}


 }
1.4修改案例申请状态
{
//管理员才拥有权限
url:
"/admin/case/apply",
methods:
"POST",
params:{
（必填）
 applyId : 1,
 caseId : 1,
 status : 0-申请中，1-申请成功，2-申请失败
},
return:{
{"status":{"timestamp":1487279568,"code":200,"msg":"更新信息成功"}}

 }

1.5 获取案例详情
{
url:
"/case/detail",
methods:
"get",
params:{
（必填）
caseId : 1,
},
return:{
{
    "status": {
        "timestamp": 1487279796,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "cid": 1,
        "userId": 1,
        "imgUrl": "/head.jpg",
        "caseName": "案例名称1",
        "level": 1,
        "description": "这是一个挺好的案例",
        "createTime": 1487209331,
        "area": "100平米",
        "state": 1
    }
}
}

2.设备接口
2.1获取设备列表
{
url:
"/goods/list",
methods:
"GET",
params:{
（可选）
goodsName : 商品名称,模糊查询
brand ： 品牌名称
mobile： 电话
status: 商品状态,integer
userId : 申请人id,integer
“page”: 1,
“rows”: 4，
},
return:{

{
    "status": {
        "total": 2,
        "timestamp": 1487229125,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "goodsId": 1,
            "goodsName": "商品名1",
            "goodsPrice": 10000,
            "description": "商品描述",
            "brand": "品牌1",
            "mobile": "18010671901",
            "address": "浙江杭州华盛达广场401室",
            "status": 0,
            "userId": 1,
            "lastUpdateTime": null,
            "img": "/dsadsad",
            "skuJson": null
        },
        {
            "goodsId": 2,
            "goodsName": "商品名2",
            "goodsPrice": 2000,
            "description": "商品描述222",
            "brand": "品牌2",
            "mobile": "18010671902",
            "address": "大大大撒打算的",
            "status": 0,
            "userId": 2,
            "lastUpdateTime": null,
            "img": "/hagag",
            "skuJson": null
        }
    ]
}

}

2.2 获取设备详情
{
url:
"/goods/detail",
methods:
"GET",
params:{
（必填）
goodsId : 1
},
return:{
{
    "status": {
        "timestamp": 1487290337,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "goodsId": 1,
        "goodsName": "商品名1",
        "goodsPrice": 10000,
        "skuJson": "{sku_info:[{/\"sku_name/\":/\"17寸_4G内存_电信/\",/\"price/\":/\"100.0/\"}]}",
        "description": "商品描述",
        "brand": "品牌1",
        "mobile": "18010671901",
        "address": "浙江杭州华盛达广场401室",
        "status": 0,
        "userId": 1,
        "lastUpdateTime": null,
        "img": "/dsadsad"
    }
}
}
2.3 发布设备
{
url:
"/goods/add",
methods:
"POST",
params:{
（必填）
goodsName : 商品名称
goodsPrice : 商品价格
description:  商品描述
userId : 用户id
type : 类型：1-智能电子；2-成品货架；3-专用材料

（可选）
brand : 牌子
mobile : 联系电话
address : 详细地址
img : 图片以#分隔

},
return:{
}

2.4 删除设备
{
url:
"/goods/delete",
methods:
"POST",
params:{
（必填）
goodsId : 设备id

},
return:{
}




3.市场接口
3.1获取市场列表
{
url:
"/market/list",
methods:
"GET",
params:{
(可选)
userId ： 市场申请人id，integer
marketName : 市场名称，String
contactName : 联系人名字，String
mobile : 联系人电话，String
status : 市场状态，Integer
page : 1,
rows : 4
},
return:{
{
    "status": {
        "total": 1,
        "timestamp": 1487232559,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "mid": 1,
            "userId": 1,
            "marketName": "培龙市场",
            "country": 0,
            "province": 3102,
            "provinceName": "山西",
            "city": 4304,
            "cityName": "临汾市",
            "district": 4391,
            "districtName": "安泽县",
            "town": 4392,
            "townName": "府城镇",
            "address": "龙湖天街",
            "area": "100平米",
            "marketCompany": "LightProject集团",
            "marketIntro": "这是一个很简短的市场介绍哦",
            "marketActivity": "满100减800",
            "createTime": 1487230209,
            "contactName": "联系人名称啦啦啦",
            "contactMobile": "18110671901",
            "meetAddress": "龙湖天街",
            "imgUrl": "/head.jpg",
            "status": 1
        }
    ]
}

}
3.2发布市场
{
url:
"/market/add",
methods:
"POST",
params:{
（必填）
userId : 1,
（可选）
marketName: 市场名称，string
province : 3102，integer
city: 4304,
district: 4391,
town: 4392,
address: 地址,String 
area : 面积，string
marketCompany ： 单位名称, String 
marketIntro : 公司简介，
contactName: 负责人名称，string
contactMobile: 负责人电话，String
meetAddress : 洽谈地址，String
imgUrl: 上传后的图片key，String //多张用#分隔
floorInfo: 楼层信息，以：分隔，如“地下一层:地上一层:地上二层”
},
return:{
{
    "status": {
        "timestamp": 1487212852,
        "code": 200,
        "msg": "插入成功"
    },
    "data": {
        "applyId": 1,//申请id
        "marketId": 2 //新增市场id
    }
}

}
3.3验证市场名称
{
url:
"/market/validname",
methods:
"GET",
params:{
（必填）
name : 市场名称,
},
return:{
{
    "status": {
        "timestamp": 1487212852,
        "code": 200,
        "msg": "插入成功"
    },
    "data": {
        "applyId": 1,//申请id
        "marketId": 2 //新增市场id
    }
}

}

3.4修改市场信息
{
url:
"/admin/market/edit",  //需要登录
methods:
"GET",
params:{
（必填）
mid : 市场id,
（可填）
    marketName : 市场名称
    country : 国家id
    province ：省份
    city ： 城市
district ： 
town
address ： 详细地址
area ： 面积
marketCompany : 市场单位
marketIntro ： 市场介绍
marketActivity ： 优惠活动
contactName ： 联系人
contactMobile ： 联系人电话
meetAddress ： 洽谈地址
imgUrl :市场图片
status ： 市场状态：0-未发布；1-使用中；2-已关闭
contractUrl ： 市场合同
},
return:{
{
    "status": {
        "timestamp": 1487212852,
        "code": 200,
        "msg": "插入成功"
    },
    "data": {
        "applyId": 1,//申请id
        "marketId": 2 //新增市场id
    }
}

}

4.上传接口
4.1获取当前上传token
{
url:
"/upload/token",
methods:
"GET",
params:{
},
return:{
{
    "status": {
        "timestamp": 1487252564,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "expire": 1487255956,
        "token": "NiHdyNvMsUUr9d1hUeXm22mJsYw_n3a0wfHsZxem:8x50OcTtIj2XKK2dYJqH9Ducj5o=:eyJzY29wZSI6ImxpZ2h0LXN0YXRpYyIsInJldHVybkJvZHkiOiJ7ICAgIFwic3RhdHVzXCI6IHsgICAgICAgIFwidGltZXN0YW1wXCI6IDE0ODcyNTI0MDYsICAgICAgICBcImNvZGVcIjogMjAwLCAgICAgICAgXCJtc2dcIjogXCJzdWNjZXNzXCIsICAgIH0sICAgIFwiZGF0YVwiOiAgICAgICAgIHsgICAgICAgICAgICBcImtleVwiOiAkKGtleSksICAgICAgICAgICAgXCJoYXNoXCI6ICQoZXRhZyksICAgICAgICAgICAgXCJ3XCI6ICQoaW1hZ2VJbmZvLndpZHRoKSwgICAgICAgICAgICBcImhcIjogJChpbWFnZUluZm8uaGVpZ2h0KSwgICAgICAgICAgICBcIm1pbWVUeXBlXCI6ICQobWltZVR5cGUpLCAgICAgICAgICAgIFwiZXh0XCI6ICQoZXh0KSAgICAgICAgICAgICAgICAgICB9fSIsImRlYWRsaW5lIjoxNDg3MjU2MDA2fQ=="
    }
}

}
4.2表单上传后返回
{
url:
"http://up-z2.qiniu.com/",
methods:
"POST",
params:{
  (必填）
  token : 根据以上接口获取的token
  file : file 
（可选）
 key : 上传后保存的名称，一般设计为路径
},
return:{
1.图片
{
    "status": {
        "timestamp": 1487252406,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "key": "FsXVHD3pBmJdZWBUIpMqgLkhYiyQ",
        "hash": "FsXVHD3pBmJdZWBUIpMqgLkhYiyQ",
        "w": 256,
        "h": 256,
        "mimeType": "image/png",
        "ext": ".png"
    }
}
2.word文档
{
    "status": {
        "timestamp": 1487252406,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "key": "FmOC_aSsVKmBaeCFa7VzYZuv9jQd",
        "hash": "FmOC_aSsVKmBaeCFa7VzYZuv9jQd",
        "w": null,
        "h": null,
        "mimeType": "application/msword",
        "ext": ".doc"
    }
}

}
4.3删除空间中的文件
{
url:
"/upload/delete",
methods:
"POST",
params:{
  (必填）
 key : 上传后保存的名称，一般设计为路径
},
return:{
}
5.地区
5.1根据父id查下级列表
{
url:
"/region/parent",
methods:
"GET",
params:{
  (必填)
  parentId : 12652
},
return:{
{
    "status": {
        "timestamp": 1487256159,
        "code": 200,
        "msg": "success"
    },
    "data": [
        {
            "id": 12653,
            "name": "西兴街道",
            "level": 4,
            "parentId": 12652
        },
        {
            "id": 12654,
            "name": "长河街道",
            "level": 4,
            "parentId": 12652
        },
        {
            "id": 12655,
            "name": "浦沿街道",
            "level": 4,
            "parentId": 12652
        }
    ]
}
}
6.摊位管理
6.1获取市场楼层摊位列表
{
url:
"/stall/list",
methods:
"GET",
params:{
  (必填)
  floorId: 1
},
return:{
{
    "status": {
        "timestamp": 1487265209,
        "code": 200,
        "msg": "success"
    },
    "data": [
        {
            "sid": 1,
            "marketId": 1,
            "floorId": 1,
            "stallName": "A1",
            "contactMobile": "18010671901",
            "contactName": "Toby",
            "createTime": 1487265136,
            "status": 1
        },
        {
            "sid": 2,
            "marketId": 1,
            "floorId": 1,
            "stallName": "A2",
            "contactMobile": "18010671901",
            "contactName": "Toby",
            "createTime": 1487265136,
            "status": 1
        }
    ]
}}
6.2 新增摊位
{
url:
"/stall/add",
methods:
"POST",
params:{
  (必填)
  floorId: 1,
  marketId : 1,
  stallName : A3,
},
return:{

{
    "status": {
        "timestamp": 1487265627,
        "code": 200,
        "msg": "success"
    },
"data": {
 	"stallId" ：3，
     "stallName": "A4" 
} 
}


}

6.3 摊位申请
{
url:
"/stall/apply",
methods:
"POST",
params:{
  (必填)
stallId : 3, //摊位id
userId : 1,
marketId : 
contactName : 联系人姓名，string
contactMobile : 联系人电话,string
stallId : 摊位id,
marketId : 市场id
floorId : 楼层id
},
return:{

{
    "status": {
        "timestamp": 1487277737,
        "code": 200,
        "msg": "success"
    },
    "data": 4
}

}
6.4 删除摊位
{
url:
"/stall/delete",
methods:
"POST",
params:{
  (必填)
stallId : 3, //摊位id
},
return:{

{
    "status": {
        "timestamp": 1487277737,
        "code": 200,
        "msg": "success"
    },
    "data": 4
}

}



7.微信用户个人信息
7.1根据userId获取用户信息
{
url:
"/wxuser/detail",
methods:
"GET",
params:{
  (必填)
  userId : 1
},
return:{

{
    "status": {
        "timestamp": 1487291493,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "userId": 1,
        "userName": "toby",
        "isLocked": false,
        "sex": 1,
        "regTime": 1487289577,
        "mobile": "15990166666",
        "email": "630444444@qq.com",
        "lastLoginTime": 1444444444,
        "openid": "AaiMp7CrNHHfXgrypodbM",
        "unionid": "",
        "province": "浙江",
        "city": "杭州",
        "country": "中国",
        "headimgurl": "http://wx.qlogo.cn/mmopen/EQv4uXLRhPJsoYMY9BbmCIDCiaDVbZNyI8OciboZ6ucDJYRQ8zMnic9whl2Gz6sM00h8DLw6HxMoLKNomTttNcBADwoOCe0KtOL/0"
    }
}

}
7.2微信链接地址
{
url:
"/response",
//微信客户端访问此链接，获取用户信息
//三天更新一次最新微信用户信息
https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6749be7c611a889c&redirect_uri=http%3a%2f%2fwytechhome.com%2fLightProject%2fresponse&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
methods:
"GET",
params:{
  (必填)
  code : 微信code
  state : 自定义字段
},
return:{
}
8.新闻管理
8.1获取新闻列表
{
url:
"/news/list",
methods:
"GET",
params:{
  (可选)
 userId : 1，
 userName: 作者名
status : 状态：1-发布中；2-发布关闭
type : 1-公司新闻 2-行业资讯
},
return:{

{
  "status": {
    "total": 16,
    "timestamp": 1490178261,
    "curPage": 16,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "nid": 1,
      "userId": 1,
      "userName": "toby",
      "content": null,
      "status": 1,
      "newsTitle": "新闻标题1",
      "createTime": 1487293259,
      "viewCount": 1230,
      "type": 1,
      "imgUrl": "news1.jpg",
      "commentCount": 1
    }
  ]
}
8.2获取新闻信息详情
{
url:
"/news/detail",
methods:
"GET",
params:{
  (必填)
 newsId : 1，
},
return:{

{
  "status": {
    "timestamp": 1490178315,
    "code": 200,
    "msg": "success"
  },
  "data": {
    "nid": 7,
    "userId": 5,
    "userName": "aaa",
    "content": "99999",
    "status": 1,
    "newsTitle": "999",
    "createTime": null,
    "viewCount": 0,
    "type": 1,
    "imgUrl": null,
    "commentCount": 0
  }
}
}
8.3发布新闻
{
url:
"/news/publish",
methods:
"POST",
params:{
  (必填)
 userId : 1，
 newsTitle : 新闻标题
（可选）
 content : 新闻内容，html代码
 type : 1-公司新闻 2-行业资讯
},
return:{

{
    "status": {
        "timestamp": 1487325243,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "newsId": 3
    }
}
}

8.4 浏览量增加
{
url:
"/news/viewadd",
methods:
"POST",
params:{
  (必填)
 nid : 新闻主键，
},
return:{
}



9.市场楼层
9.1根据市场获取楼层列表
{
url:
"/floor/market",
methods:
"GET",
params:{
  (必填)
 marketId : 1，
},
return:{
{
    "status": {
        "total": 3,
        "timestamp": 1487302110,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 3
    },
    "data": [
        {
            "fid": 1,
            "floorName": "地下一层",
            "isUsed": true,
            "marketId": 1
        },
        {
            "fid": 2,
            "floorName": "地上一层",
            "isUsed": true,
            "marketId": 1
        },
        {
            "fid": 3,
            "floorName": "地上二层",
            "isUsed": true,
            "marketId": 1
        }
    ]
}


}

10.收藏
10.1获取收藏列表
{
url:
"/like/list",
methods:
"GET",
params:{
  (必填)
 userId: 1，
 （可选）
page : 1,
rows : 30,
type ： //收藏的类型：1案例，2.招商，3设备 ,4 新闻，5页面
 //type为1时，contentId为案例id；2时，市场id，3时，设备id；5时，contentId无效，content为页面key
userId : 当前微信用户id,

},
return:{
{
  "status": {
    "total": 6,
    "timestamp": 1490034378,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "id": 1,
      "userId": 1,
      "contentId": 1,
      "content": "type1时案例id",
      "type": 1,
      "createTime": 1487319949
    },
    {
      "id": 2,
      "userId": 1,
      "contentId": 2,
      "content": "招商",
      "type": 2,
      "createTime": 1487319949
    },
    {
      "id": 3,
      "userId": 1,
      "contentId": 1,
      "content": "设备",
      "type": 3,
      "createTime": 1487319949
    },
    {
      "id": 4,
      "userId": 1,
      "contentId": 1,
      "content": "新闻",
      "type": 4,
      "createTime": 1487319949
    },
    {
      "id": 5,
      "userId": 1,
      "contentId": 1,
      "content": "页面key",
      "type": 5,
      "createTime": 1487321567
    },
    {
      "id": 6,
      "userId": 1,
      "contentId": 22,
      "content": null,
      "type": 1,
      "createTime": 1490034127
    }
  ]
}
}


10.2 加入收藏
{
url:
"/like/add",
methods:
"POST",
params:{
  (必填)
 userId: 1，
 type ：  //收藏的类型：1案例，2.招商，3设备 ,4 新闻，5页面
contentId : 1,（type=5时，contentId可为空）
 （可选）
content : 30,
 //type为1时，contentId为案例id；2时，市场id，3时，设备id；5时，contentId无效，content为页面key
},
return:{
{
  "status": {
    "timestamp": 1490037547,
    "code": 200,
    "msg": "success"
  },
  "data": 7
}}

10.3 是否已收藏
{
url:
"/like/islike",
methods:
"POST",
params:{
  (必填)
 userId: 1，
 type ：  //收藏的类型：1案例，2.招商，3设备 ,4 新闻，5页面
contentId : 1,（type=5时，contentId可为空）
 
 //type为1时，contentId为案例id；2时，市场id，3时，设备id；5时，contentId无效，content为页面key
},
return:{
{
  "status": {
    "timestamp": 1490034176,
    "code": 200,
    "msg": "success"
  },
  "data": {
    "id": 6, //未收藏时，id为0
    "isLike": 1 //1为已收藏，0为未收藏
  }
}}

10.4 取消收藏
{
url:
"/like/delete",
methods:
"POST",
params:{
  (必填)
 id ： 主键
},
return:{
{
  "status": {
    "timestamp": 1490034176,
    "code": 200,
    "msg": "success"
  }
}}



11.发布与审批管理
11.1获取个人案例发布信息
{
url:
"/apply/caselist",
methods:
"GET",
params:{
  （可选）
 page : 1，
 rows : 30,
 caidId : 案例申请id
 userId : 申请人id，
 state : 0-申请中，1-申请成功，2-申请失败
 caseId ： 案例id
},
return:{

{
    "status": {
        "total": 3,
        "timestamp": 1487561927,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "caid": 1,
            "userId": 1,
            "caseId": 6,
            "createTime": 1487212852,
            "state": 1,
            "caseName": "tdadasd",
            "area": "90"
        },
        {
            "caid": 3,
            "userId": 1,
            "caseId": 4,
            "createTime": 1487213655,
            "state": 1,
            "caseName": "案例名称3",
            "area": "30平米"
        },
        {
            "caid": 2,
            "userId": 2,
            "caseId": 8,
            "createTime": 1487213602,
            "state": 0,
            "caseName": "case4",
            "area": "90"
        }
    ]
}
}

11.2获取个人市场发布信息
{
url:
"/apply/marketlist",
methods:
"GET",
params:{
  （可选）
 page : 1，
 rows : 30,
 maid : 市场申请id
 userId : 申请人id，
 marketId : 市场id
 status : 0-申请中，1-申请成功，2-申请失败
 contactName： 联系人名称
 contactMobile : 联系人电话 
},
return:{

{
    "status": {
        "total": 2,
        "timestamp": 1487569840,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "maid": 1,
            "userId": 1,
            "marketId": 2,
            "createTime": 1487254379,
            "status": 0,
            "marketName": "tobyMarket",
            "contactName": "toby",
            "contactMobile": "18010671902"
        },
        {
            "maid": 2,
            "userId": 1,
            "marketId": 3,
            "createTime": 1487259851,
            "status": 0,
            "marketName": "lalla",
            "contactName": "toby",
            "contactMobile": "18010671903"
        }
    ]
}}




11.3获取个人摊位发布信息
{
url:
"/apply/stalllist",
methods:
"GET",
params:{
  （可选）
 page : 1，
 rows : 30,
 said : 摊位申请id
 userId : 申请人id，
 stalllId : 摊位id
 status : 0-申请中，1-申请成功，2-申请失败
 marketName： 市场名称
 stallName : 摊位名称

marketId : 市场id add on 0320_2116
},
return:{
{
  "status": {
    "total": 8,
    "timestamp": 1490018029,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "said": 4,
      "userId": 1,
      "createTime": 1487277737,
      "status": 0,
      "contactName": null,
      "contactMobile": null,
      "stallId": 3,
      "stallName": null,
      "marketId": null,
      "marketName": null,
      "floorId": null,
      "floorName": null
    },
    {
      "said": 6,
      "userId": 1,
      "createTime": 1487571733,
      "status": 0,
      "contactName": null,
      "contactMobile": null,
      "stallId": 2,
      "stallName": null,
      "marketId": null,
      "marketName": null,
      "floorId": null,
      "floorName": null
    }
  ]
}



}



11.4获取个人新闻发布信息
{
url:
"/apply/newslist",
methods:
"GET",
params:{
  （可选）
 page : 1，
 rows : 30,
 naid: 新闻申请id
 userId : 申请人id，
 newsId : 新闻id
 status : 0-申请中，1-申请成功，2-申请失败

 newsTitle： 新闻标题
 type : 新闻类型
 
},
return:{

{
    "status": {
        "total": 1,
        "timestamp": 1487581042,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "naid": 1,
            "userId": 1,
            "newsId": 2,
            "createTime": 1487325222,
            "status": 0,
            "newsTitle": "hahha",
            "type": null
        }
    ]
}

}

11.* 获取个人加盟发布信息
{
url:
"/apply/cooperatelist",
methods:
"GET",
params:{
 cid : id
 userId : 申请人id
 order : 排序，格式为 字段名+排序，
例如 create_time desc 为按时间降序
state : 0-申请中，1-申请成功，2-申请失败
},
return:{
{
  "status": {
    "total": 1,
    "timestamp": 1489550090,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "cid": 1,
      "userId": 1,
      "cooperateCompanyId": 1,
      "createTime": 1487298291,
      "state": 0,
      "fullName": "fullCompanyname"
    }
  ]
}
11.* 获取个人设备发布信息
{
url:
"/apply/goodslist",
methods:
"GET",
params:{
（可选）
 gaid : id
 order : 排序，格式为 字段名+排序，
例如 create_time desc 为按时间降序
status : 0-申请中，1-申请成功，2-申请失败
userId : 申请人id
},
return:{
{
  "status": {
    "total": 2,
    "timestamp": 1489578224,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "gaid": 1,
      "goodsId": 1,
      "createTime": 1234567890,
      "status": 0,
      "goodsName": "商品名1",
      "img": "goods1.jpg#goods2.jpg"
    },
    {
      "gaid": 2,
      "goodsId": 2,
      "createTime": 1234567890,
      "status": 0,
      "goodsName": "商品名2",
      "img": "goods3.jpg#goods4.jpg"
    }
  ]
}
}



11.5审核案例
{
url:
"/admin/apply/appcase",
methods:
"post",
params:{
  （必填）
 caseApplyId : 案例申请id
 status : 1-成功；2-失败
 
},
return:{
}

11.6审核市场
{
url:
"/admin/apply/appmarket",
methods:
"post",
params:{
  （必填）
 marketApplyId : 市场申请id
 status : 1-成功；2-失败
 
},
return:{
}
11.7审核摊位
{
url:
"/admin/apply/appstall",
methods:
"post",
params:{
  （必填）
 stallApplyId : 市场申请id
 status : 1-成功；2-失败
 
},
return:{
}

11.8审核新闻
{
url:
"/admin/apply/appnews",
methods:
"post",
params:{
  （必填）
 newsApplyId : 市场申请id
 status : 1-成功；2-失败
 
},
return:{
}
11.9审核加盟
{
url:
"/admin/apply/appcooperate",
methods:
"post",
params:{
  （必填）
 cooperateApplyId : 市场申请id
 status : 1-成功；2-失败
 
},
return:{
}
11.10审核设备
{
url:
"/admin/apply/appgoods",
methods:
"post",
params:{
  （必填）
 goodsApplyId : 设备申请id
 status : 1-成功；2-失败
 
},
return:{
}

11.10 统一删除接口
{
url:
"/admin/apply/mdelete",
methods:
"post",
params:{
  （必填）
 type : 类型，1-案例，2-市场，3-新闻，4-加盟，5-设备
 typeId: 对应类型i主键d
 
},
return:{
}



12.后台登录与退出
12.1 账号密码登录
{
url:
"/redirect",     //所有/admin开头的都需要登录
methods:
"POST",
params:{
 （必填）
 username : 用户账号
 password : 密码，32位MD5加密
 inpcode : 验证码，4位 
 roleId : 1-管理员；admin
2-会计；kuai
3-员工；em
4-客户;cus
密码 1234
},
return:{

{
    "status": {
        "timestamp": 1488943611,
        "code": 200,
        "msg": "登录成功"
    },
    "data": {
        "employeeId": 2, //员工返回此字段。客户返回 customerId
        "username": "cus",
        "userId": 2,
        "roleId": 2
    }
}
}
12.2 获取验证码（图片）
{
url:
"/validcode",     
methods:
"GET",
params:{
},
return:{

}

12.3 退出
{
url:
"/logout",     
methods:
"POST",
params:{
type : 退出类型 0-管理员、会计登录注销
                    1-员工注销
                    2-客户注销
openId : 
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    
}
}
12.4 员工和客户账号登录后，拉取相关微信信息
{
url:
"/wxlogin",     
methods:
"get",
params:{
code : 微信code
state :登录的用户id
 账号密码登录后，获取openId处理
 * 具体用法为，每次登录后，将下面链接中的state=loginUserId，传入登录的id，
之后会自动跳转到用户个人中心首页。
     * 每次用户登录，都需要获取其最新的openId,
     * 查找数据库，如果没有其openId,1.对于员工则进行更新。2.对于客户则进行添加映射表
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6749be7c611a889c&redirect_uri=http%3a%2f%2fwytechhome.com%2fLightProject%2fwxlogin&response_type=code&scope=snsapi_userinfo&state=loginUserId#wechat_redirect
},
return:{
//访问后跳转的链接为：
http://wytechhome.com/wechatTest/GYAdmin/html/perInfo/index.html?openId=XXXXX
在本地需要缓存openId，在退出时会用到
}

12.5 获取登录账号列表
{
url:
"/admin/login/userlist",     
methods:
"get",
params:{
（可选）
page : 
rows : 
id : 主键
username : 账号名
roleIds : 登录角色 ： 1-管理员；2-会计；3-员工；4-客户
isLocked : 0-正常； 1-冻结
nickName : 昵称，模糊匹配

},
return:{
{
    "status": {
        "total": 9,
        "timestamp": 1489625967,
        "curPage": 5,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "id": 1,
            "userTypeId": 0,
            "username": "admin",
            "password": "",
            "roleIds": "1",
            "isLocked": 0,
            "userType": 1,
            "nickName": "韩跑跑",
            "openId": "",
            "unionid": "",
            "headImg": "head1.jpg"
        },
        {
            "id": 2,
            "userTypeId": 1,
            "username": "kuai",
            "password": "",
            "roleIds": "2",
            "isLocked": 0,
            "userType": 1,
            "nickName": "kuaiji",
            "openId": "",
            "unionid": "",
            "headImg": "head2.jpg"
        }
    ]
}

}
12.6 添加会计或管理员账号
{
url:
"/admin/config/addacc",     
methods:
"POST",
params:{
（必填）
username : 账号
password : 密码
roleIds : 1-管理员；2-会计
（可选）
nickName :昵称
headImg : 头像

},
return:{
}
12.7 编辑会计或管理员账号
{
url:
"/admin/config/editacc",     
methods:
"POST",
params:{
（必填）
id :  
（可选）
username : 账号
password : 密码
roleIds : 1-管理员；2-会计
nickName :昵称
headImg : 头像
isLocked : 0-正常；1-冻结
},
return:{
}

12.9 根据type和openId获取用户详细信息
{
url:
"/admin/login/userdetail",     
methods:
"get",
params:{
（必填）
type : 1-员工；2-客户
openId 
},
return:{
{
  "status": {
    "timestamp": 1489804577,
    "code": 200,
    "msg": "success"
  },
  "data": {
    "loginUserId": 4,
    "nickname": "tobyHong_em",
    "headimgurl": "head4.jpg"
  }
}
}


***验证用户是否登录（测试）
{
url:
"/info",     
methods:
"GET",
params:{
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": "用户名为：toby"
}
}




13.入账
13.1 提交入账信息
{
url:
"/admin/account/commit",     
methods:
"POST",
params:{
  receiveDate : 到账日期，integer
  marketId : 市场id，integer //页面有获取市场接口
  marketName : 市场名，//从上述接口获取
  //额外信息内容：0-款项类型；1-收款银行
  typeId : 款项类型, integer //从“获取入账额外信息”接口获取，
  receiveBank : 收款银行，int,从“获取入账额外信息”接口获取
  receiveMoney : 到账金额，int,以分为单位
  remark : 备注
  invoiceStatus : 开票状态：0-未开；1-已开
  invoiceTime : 开票时间	
  invoicePic : 上传发票url
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}





13.2 获取入账列表
{
url:
"/admin/account/list",     
methods:
"GET",
params:{
  page : 1,
  rows ： 30，
  aid : 1，入账id
  typeId : 款项类型0为未知

  status : 入账状态：0-未审核；1-已审核通过；2-审核不通过
},
return:{
{
  "status": {
    "total": 1,
    "timestamp": 1489398227,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "aid": 1,
      "receiveDate": 1487644240,
      "marketId": 2,
      "marketName": "tobymarket",
      "typeId": 1,
      "receiveBank": 22,
      "receiveMoney": 140000,
      "remark": "这是备注",
      "invoiceStatus": 0,
      "invoiceTime": 1487644240,
      "invoicePic": null,
      "updateTime": 1487667144,
      "operateNo": 1,
      "loginUserId": 1,
      "status": 0,
      "projectId": 1,
      "projectMarketName": "培龙市场",
      "typeName": "设计合同阶段"
    }
  ]
}
}

13.3 获取入账额外信息
{
url:
"/admin/account/extralist",     
methods:
"GET",
params:{
  id : 1，
  typeName : 类型名称，
  typeContent : 额外信息内容：0-款项类型；1-收款银行
},
return:{

{
    "status": {
        "total": 22,
        "timestamp": 1487644422,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 22
    },
    "data": [
        {
            "id": 1,
            "typeName": "设计合同阶段",
            "typeContent": 0
        },
        {
            "id": 2,
            "typeName": "设计平面阶段",
            "typeContent": 0
        },
        {
            "id": 3,
            "typeName": "设计效果图阶段",
            "typeContent": 0
        },
        {
            "id": 4,
            "typeName": "设计施工图交接阶段",
            "typeContent": 0
        },
        {
            "id": 5,
            "typeName": "设计施工装修阶段",
            "typeContent": 0
        },
        {
            "id": 6,
            "typeName": "设计竣工开业阶段",
            "typeContent": 0
        },
        {
            "id": 7,
            "typeName": "管理",
            "typeContent": 0
        },
        {
            "id": 8,
            "typeName": "设备",
            "typeContent": 0
        },
        {
            "id": 9,
            "typeName": "建筑",
            "typeContent": 0
        },
        {
            "id": 10,
            "typeName": "定位",
            "typeContent": 0
        },
        {
            "id": 11,
            "typeName": "招商",
            "typeContent": 0
        },
        {
            "id": 12,
            "typeName": "电商",
            "typeContent": 0
        },
        {
            "id": 13,
            "typeName": "其他",
            "typeContent": 0
        },
        {
            "id": 14,
            "typeName": "LightProject联合",
            "typeContent": 1
        },
        {
            "id": 15,
            "typeName": "LightProject招商",
            "typeContent": 1
        },
        {
            "id": 16,
            "typeName": "菜源招商",
            "typeContent": 1
        },
        {
            "id": 17,
            "typeName": "现金",
            "typeContent": 1
        },
        {
            "id": 18,
            "typeName": "name建行",
            "typeContent": 1
        },
        {
            "id": 19,
            "typeName": "name农行",
            "typeContent": 1
        },
        {
            "id": 20,
            "typeName": "name工行",
            "typeContent": 1
        },
        {
            "id": 21,
            "typeName": "LightProject工作室",
            "typeContent": 1
        },
        {
            "id": 22,
            "typeName": "其他银行",
            "typeContent": 1
        }
    ]
}
}
13.4 修改入账信息
{
url:
"/admin/account/edit",     
methods:
"POST",
params:{
  receiveDate : 到账日期，integer
  marketId : 市场id，integer //页面有获取市场接口
  marketName : 市场名，//从上述接口获取
  //额外信息内容：0-款项类型；1-收款银行
  typeId : 款项类型, integer //从“获取入账额外信息”接口获取，
  receiveBank : 收款银行，int,从“获取入账额外信息”接口获取
  receiveMoney : 到账金额，int,以分为单位
  remark : 备注
  invoiceStatus : 开票状态：0-未开；1-已开
  invoiceTime : 开票时间	
  invoicePic : 上传发票url
 // status : 入账状态：0-未审核；1-已审核；2-已关闭 此字段不能再这里修改
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}

13.5 删除入账信息
{
url:
"/admin/account/delete",     
methods:
"POST",
params:{
 id： 
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}
13.6 确定/取消入账
{
url:
"/admin/account/delete",     
methods:
"POST",
params:{
 id : 
 status : 入账状态：0-未审核；1-已审核；2-已关闭//确定该条记录审核成功 
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}



14.分红
14.1 提交分红
{
url:
"/admin/bonus/commit",     
methods:
"POST",
params:{
（如果是会计提交时，多传一个字段：updateTime : 分红计入通过时间）
 projectId : 工程id
 marketName : 市场名称
 typeId : 类型，//从获取额外信息接口
 receiveMoney : 到账金额，int
 travelMoney : 差率，金额int
 rebateMoney : 回扣, int
 otherMoney : 其他
 realMoney : 公司真实收入
 bonusNum : 分红人数
 personBusSalary : 个人业绩收入
 dividendRadio : 分红比例，1-100
 bonusLevel : 分红级别，获取用户目前总金额数
 bonusMoney : 分红金额
 remark : 备注

},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}

14.2 获取分红列表
{
url:
"/admin/bonus/list",     
methods:
"GET",
params:{
 （可选）
  page : 1,
  rows ： 30，
  bid : 1，分红id
  
  marketName : 市场名称 add on 0320_2042

  startTime : 分红审核通过时间，起始时间
  endTime : 分红审核通过时间，结束时间

 
  status : 分红记录状态：0-未审核；1-审核通过；2-审核关闭
},
return:{
{
    "status": {
        "total": 5,
        "timestamp": 1489404726,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "bonusIds": [
        {
          "id": 1,
          "employeeId": 1,
          "bonusId": 1,
          "employeeName": "dada"
        },
        {
          "id": 2,
          "employeeId": 2,
          "bonusId": 1,
          "employeeName": "toby"
        }
      ],
      "contractIds": [
        {
          "employeeId": 1,
          "employeeName": "ename1"
        },
        {
          "employeeId": 2,
          "employeeName": "ename2"
        },
        {
          "employeeId": 3,
          "employeeName": "ename3"
        }
      ],
      "bonus": {
        "bid": 1,
        "marketId": 1,
        "contractids": "1:2:3",
        "projectId": null,
        "marketName": "培龙市场",
        "typeId": 1,
        "receiveMoney": null,
        "travelMoney": null,
        "rebateMoney": null,
        "otherMoney": null,
        "realMoney": null,
        "bonusNum": null,
        "personBusSalary": 1000,
        "dividendRadio": null,
        "bonusLevel": null,
        "bonusMoney": 2500,
        "remark": null,
        "bonusTime": null,
        "operateNo": null,
        "updateTime": null,
        "loginUserId": null,
        "status": 2
      }
    }
    ]
}
}
14.3 获取分红类型
{
url:
"/admin/bonus/extralist",     
methods:
"GET",
params:{
 （可选）
  page : 1,
  rows ： 30，
  bid : 1，分红id
  marketId : 市场ID
},
return:{
{
    "status": {
        "total": 13,
        "timestamp": 1487649477,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 13
    },
    "data": [
        {
            "id": 1,
            "bonusType": "出差"
        },
        {
            "id": 2,
            "bonusType": "合同阶段"
        },
        {
            "id": 3,
            "bonusType": "平面阶段"
        },
        {
            "id": 4,
            "bonusType": "效果图阶段"
        },
        {
            "id": 5,
            "bonusType": "施工交接阶段"
        },
        {
            "id": 6,
            "bonusType": "施工装修阶段"
        },
        {
            "id": 7,
            "bonusType": "竣工开业阶段"
        },
        {
            "id": 8,
            "bonusType": "管理培训推广"
        },
        {
            "id": 9,
            "bonusType": "管理系统推广"
        },
        {
            "id": 10,
            "bonusType": "管理教材推广"
        },
        {
            "id": 11,
            "bonusType": "电商推广"
        },
        {
            "id": 12,
            "bonusType": "设备推广"
        },
        {
            "id": 13,
            "bonusType": "招商推广"
        }
    ]
}
}

14.4 获取分红级别
{
url:
"/admin/bonus/level",     
methods:
"POST",
params:{
bonusIds : 员工ids
},
return:{

{
    "status": {
        "timestamp": 1487900706,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "total": 4000,
        "eids": "1:2",
        "num": 2,
        "average": 2000
    }
}}

14.5 管理员审核分红记录
{
url:
"/admin/bonus/confirm",     
methods:
"POST",
params:{
id : 分红记录id
status : 分红记录状态：0-未审核；1-审核通过；2-审核不通过
        不可设置为3,3为删除，会报错。
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}
14.6 删除分红记录
{
url:
"/admin/bonus/delete",     
methods:
"POST",
params:{
id : 分红记录id
  //删除为标记删除
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}

14.7 编辑分红记录
{
url:
"/admin/bonus/edit",     
methods:
"POST",
params:{
（必填）
bid : 分红记录id

 marketId : 市场id
 marketName : 市场名称
 typeId : 类型，//从获取额外信息接口
 receiveMoney : 到账金额，int
 travelMoney : 差率，金额int
 rebateMoney : 回扣, int
 otherMoney : 其他
 realMoney : 公司真实收入
 bonusNum : 分红人数
 personBusSalary : 个人业绩收入
 dividendRadio : 分红比例，1-100
 bonusLevel : 分红级别，获取用户目前总金额数
 bonusMoney : 分红金额
 remark : 备注
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}
14.8 编辑分红类型
{
url:
"/admin/bonus/extraedit",     
methods:
"POST",
params:{
（必填）
 id: 主键
bonusType : 分红类型名字
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}
14.9 删除分红类型
{
url:
"/admin/bonus/extradelete",     
methods:
"POST",
params:{
（必填）
 id: 主键
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}
14.10 添加分红类型
{
url:
"/admin/bonus/extraadd",     
methods:
"POST",
params:{
（必填）
 id: 主键
bonusType : 分红类型名称
},
return:{

{
    "status": {
        "timestamp": 1487640024,
        "code": 200,
        "msg": "success"
    },
    "data": null
}
}

14.11 获取分红比例列表
{
url:
"/admin/bonus/levellist",     
methods:
"GET",
params:{
},
return:{
}

14.12 添加分红比例
{
url:
"/admin/bonus/leveladd",     
methods:
"GET",
params:{
（必填）
bonusRate: 分红比例
（选填）
bonusLevelName: 分红比例级别名称
bonusLevelMoney: 分红所需金额
},
return:{
}
14.12 编辑分红比例
{
url:
"/admin/bonus/leveledit",     
methods:
"POST",
params:{
（必填）
id ： 主键
（选填）
bonusRate: 分红比例
bonusLevelName: 分红比例级别名称
bonusLevelMoney: 分红所需金额
},
return:{
}
14.13 删除分红比例
{
url:
"/admin/bonus/leveldelete",     
methods:
"POST",
params:{
（必填）
id ： 主键
},
return:{
}




15.员工管理
15.1 注册员工
{
url:
"/admin/employee/commit",     
methods:
"POST",
params:{
 name : 名称，string
 job : 职称，1-设计师，2-合同人，3-会计
 companyName : 单位名称
 companyAddress : 单位地址
 companyAccount ： 单位账户，string
 accountBank : 银行支行名称
 birthday : int 出生
 sex : 性别 0-保密；1-男；2-女
 idcardNumber : 身份证号码
 idcardAddress : 身份证上地址
 idcardFrontUrl :身份证正面url
 idcardBehindUrl : 身份证背面url
 education : 学历证书url
 contact ： 合同url
 probationBaseSalary : 使用薪资，int
 officialSalaryOne : 正式底薪一
 officialSalaryTwo : 正式底薪二
 officialSalaryThree : 正式底薪三
 officialSalaryFour ：正式底薪四
 mobile ： 手机
 qq ：
 wx ：
 dearFriendName ：亲密好友姓名
 dearFriendMobile ： 亲密好友联系方式
 entryTime : 入职时间


},
return:{

{
    "status": {
        "timestamp": 1487663457,
        "code": 200,
        "msg": "success"
    },
    "data": {
        "employeeId": 2,
        "loginUserId": 2
    }
}}
15.2 获取员工列表
{
url:
"/admin/employee/list",     
methods:
"GET",
params:{ 
 （可选）
 page ： 1
 rows : 30
 eid : 员工id
 name : 员工姓名
  job: //职称，1-设计师，2-合同人，3-会计


 status : 员工状态：0-正常；1-冻结
},
return:{

{
    "status": {
        "total": 2,
        "timestamp": 1487663684,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "eid": 1,
            "name": "名称",
            "job": 2,
            "companyName": "单位名称",
            "companyAccount": "单位账户，string",
            "accountBank": "银行支行名称",
            "birthday": 14444444,
            "sex": 0,
            "idcardNumber": "460004199408180018",
            "idcardAddress": "身份证上地址",
            "idcardFrontUrl": "身份证正面url",
            "idcardBehindUrl": "身份证背面url",
            "education": "学历证书url",
            "contact": "合同url",
            "probationBaseSalary": 0,
            "officialSalaryOne": 0,
            "officialSalaryTwo": 0,
            "officialSalaryThree": 0,
            "officialSalaryFour": 0,
            "mobile": "手机",
            "qq": null,
            "wx": null,
            "dearFriendName": "亲密好友姓名",
            "dearFriendMobile": "亲密好友联系方式",
            "registerTime": 1487663405,
            "entryTime": 1487663405,
            "companyAddress": null

              "username": zhanghao
 "password": mima
        }
     ]
 }
}

15.3 编辑员工
{
url:
"/admin/employee/edit",     
methods:
"POST",
params:{
 name : 名称，string
 job : 职称，1-设计师，2-合同人
 companyName : 单位名称
 companyAddress : 单位地址
 companyAccount ： 单位账户，string
 accountBank : 银行支行名称
 birthday : int 出生
 sex : 性别 0-保密；1-男；2-女
 idcardNumber : 身份证号码
 idcardAddress : 身份证上地址
 idcardFrontUrl :身份证正面url
 idcardBehindUrl : 身份证背面url
 education : 学历证书url
 contact ： 合同url
 probationBaseSalary : 使用薪资，int
 officialSalaryOne : 正式底薪一
 officialSalaryTwo : 正式底薪二
 officialSalaryThree : 正式底薪三
 officialSalaryFour ：正式底薪四
 mobile ： 手机
 qq ：
 wx ：
 dearFriendName ：亲密好友姓名
 dearFriendMobile ： 亲密好友联系方式
 entryTime : 入职时间

status : 员工状态：0-正常；1-冻结；2-标记删除
    //2属性不能设置，会报错

return:{
  
}
15.4 标记删除员工
{
url:
"/admin/employee/delete",     
methods:
"POST",
params:{
  eid : 主键
return:{
  
}

15.5 获取除会计外的员工列表
{
url:
"/admin/employee/list",     
methods:
"GET",
params:{ 
 （可选）
 page ： 1
 rows : 30
 eid : 员工id
 name : 员工姓名
  job: //职称，1-设计师，2-合同人，3-会计



 status : 员工状态：0-正常；1-冻结
},
return:{

{
    "status": {
        "total": 2,
        "timestamp": 1487663684,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "eid": 1,
            "name": "名称",
            "job": 2,
            "companyName": "单位名称",
            "companyAccount": "单位账户，string",
            "accountBank": "银行支行名称",
            "birthday": 14444444,
            "sex": 0,
            "idcardNumber": "460004199408180018",
            "idcardAddress": "身份证上地址",
            "idcardFrontUrl": "身份证正面url",
            "idcardBehindUrl": "身份证背面url",
            "education": "学历证书url",
            "contact": "合同url",
            "probationBaseSalary": 0,
            "officialSalaryOne": 0,
            "officialSalaryTwo": 0,
            "officialSalaryThree": 0,
            "officialSalaryFour": 0,
            "mobile": "手机",
            "qq": null,
            "wx": null,
            "dearFriendName": "亲密好友姓名",
            "dearFriendMobile": "亲密好友联系方式",
            "registerTime": 1487663405,
            "entryTime": 1487663405,
            "companyAddress": null
        }
     ]
 }
}


16.合作方档案
合作方档案更改一下---无需审核
16.1 提交合作方档案
{
url:
"/admin/partner/commit",     
methods:
"POST",
params:{ 
cooperate_name : 合作方名称
cooperateContent : 合作内容介绍
chiefPerson : 负责人姓名
mobile : 合作电话
address : 合作地址
bankAccount : 银行账户
bankAccountNo : 账号
bankBranch : 支行
remark : 备注
},
return:{
}
16.2 获取合作方列表
{
url:
"/admin/partner/list",     
methods:
"GET",
params:{ 
 （可选）
 page ： 1
 rows : 30
 pid : 员工id
 chiefPerson : 主要负责人姓名
 status : 合作状态：0-审核中；1-合作中；2-合作关闭
},
return:{

{
  "status": {
    "total": 1,
    "timestamp": 1489390855,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "pid": 3,
      "cooperateContent": "dadsad",
      "chiefPerson": null,
      "mobile": null,
      "address": null,
      "bankAccount": null,
      "bankAccountNo": null,
      "bankBranch": null,
      "remark": null,
      "createTime": 1487942440,
      "status": 0,
      "cooperateName": null
    }
  ]
}    ]
}

}
16.3 删除合作方
{
url:
"/admin/partner/delete",     
methods:
"POST",
params:{ 
  （必填）
  pid : 合作方id
},
return:{
}
16.4 修改合作方信息
{
url:
"/admin/partner/edit",     
methods:
"POST",
params:{ 
（必填）
pid : 主键

cooperate_name : 合作方名称
cooperateContent : 合作内容介绍
chiefPerson : 负责人姓名
mobile : 合作电话
address : 合作地址
bankAccount : 银行账户
bankAccountNo : 账号
bankBranch : 支行
remark : 备注
//合作方无需审核，默认把0状态删掉
status : 合作状态：0-审核中；1-合作中；2-合作关闭；3-标记删除
     //不能为3，会报错
},
return:{
}


17.工程管理
17.1 提交工程（已改）
{
url:
"/admin/project/commit",     
methods:
"POST",
params:{ 
（必填）
marketName : 市场name
customerId : 单位id，客户档案调用


createTime : 项目合同日期
realContactMoney : 实际合同价,等于=总价+差旅金额+回扣+其他
contactMoney :合同价
firstMoney : 第一笔
firstFinishTime : 完成时间
secondMoney : 第二笔
secondFinishTime : 完成时间
thirdMoney : 第三笔
thirdFinishTime : 完成时间
fourMoney : 第四笔
fourFinishTime : 完成时间
fiveMoney : 第五笔
fiveFinishTime : 完成时间
remark : 备注
busTravelType : 差旅类型：0-甲方全报销；1-甲方报销交通；2-甲方报销住宿；3-乙方全报销
travelMoney : 差旅金额
rebateMoney : 回扣金额
extraMoney : 其他
signingCompany : 签约公司：0-LightProject；1-菜源；2-其他

contractUrls ： 上传合同图片s，以#分隔
contractIds ：合同人ids 以:分隔

//市场具体地址信息
province : 
city:
district:
marketAddress :详细地址
marketIntro : 市场介绍

//status 默认设置为1

},
return:{
}
17.2 获取工程列表
{
url:
"/admin/project/list",     
methods:
"POST",
params:{ 
(可选)
pid ： 工程id
marketName : 市场名称
status : 工程状态：0-未开始；1-进行中；2-审核失败；3-审核成功；4-成功结束

employeeId : 员工id
return:{
{
    "status": {
        "total": 5,
        "timestamp": 1489408016,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "project": {
                "pid": 1,
                "createTime": 1480605780,
                "marketId": 0,
                "marketName": "培龙市场",
                "customerId": 1,
                "customerName": "单位名称（客户档案调用）",
                "realContactMoney": 0,
                "contactMoney": 0,
                "firstMoney": 0,
                "firstFinishTime": 1483261200,
                "secondMoney": 0,
                "secondFinishTime": null,
                "thirdMoney": 0,
                "thirdFinishTime": null,
                "fourMoney": 0,
                "fourFinishTime": null,
                "fiveMoney": 0,
                "fiveFinishTime": null,
                "remark": "",
                "busTravelType": 0,
                "travelMoney": 0,
                "rebateMoney": 0,
                "extraMoney": 0,
                "signingCompany": 0,
                "province": 1,
                "city": 2,
                "district": 3,
                "town": 4,
                "marketAddress": "",
                "marketIntro": "",
                "status": 0,
                "processId": 15,
                "loginUserId": 3
            },
            "list": [
                {
                    "id": 1,
                    "employeeId": 3,
                    "projectId": 1,
                    "employeeName": "em1"
                }
            ]
        }

}

17.3 删除工程
{
url:
"/admin/project/delete,     
methods:
"POST",
params:{ 
  pid : 工程表id
},
return:{


}


17.4 编辑工程（已改）
{
url:
"/admin/project/edit",     
methods:
"POST",
params:{ 
（必填）
pid : 主键


***processId : 此接口不能修改工程进度

createTime : 项目合同日期
marketId : 市场id
customerId : 单位id，客户档案调用
realContactMoney : 实际合同价,等于=总价+差旅金额+回扣+其他
contactMoney :合同价
firstMoney : 第一笔
firstFinishTime : 完成时间
secondMoney : 第二笔
secondFinishTime : 完成时间
thirdMoney : 第三笔
thirdFinishTime : 完成时间
fourMoney : 第四笔
fourFinishTime : 完成时间
fiveMoney : 第五笔
fiveFinishTime : 完成时间
remark : 备注
busTravelType : 差旅类型：0-甲方全报销；1-甲方报销交通；2-甲方报销住宿；3-乙方全报销
travelMoney : 差旅金额
rebateMoney : 回扣金额
extraMoney : 其他
signingCompany : 签约公司：0-LightProject；1-菜源；2-其他

status ： 工程状态：0-未开始；1-进行中；2-审核失败；3-审核成功；4-成功结束；5-标记删除
    //不能设为5，删除使用删除接口
},
return:{
}
17.5 验证市场名是否唯一（已改）
{
url:
"/admin/project/valid",     
methods:
"GET",
params:{ 
（必填）
 marketName : 市场名称
},
return:{

{
  "status": {
    "timestamp": 1488356327,
    "code": 200,
    "msg": "success"
  },
  "data": false //true为存在市场名称，false为市场名称未注册
}
}
17.6 根据员工id获取工程列表
{
url:
"/admin/project/eplist",     
methods:
"GET",
params:{ 
（必填）
 userId : 登录id
},
return:{

{
    "status": {
        "total": 4,
        "timestamp": 1488771739,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "pid": 1,
            "createTime": null,
            "marketId": null,
            "marketName": "培龙市场",
            "customerId": null,
            "customerName": "单位名称（客户档案调用）",
            "realContactMoney": 0,
            "contactMoney": null,
            "firstMoney": null,
            "firstFinishTime": null,
            "secondMoney": null,
            "secondFinishTime": null,
            "thirdMoney": null,
            "thirdFinishTime": null,
            "fourMoney": null,
            "fourFinishTime": null,
            "fiveMoney": null,
            "fiveFinishTime": null,
            "remark": null,
            "busTravelType": null,
            "travelMoney": null,
            "rebateMoney": null,
            "extraMoney": null,
            "signingCompany": null,
            "province": null,
            "city": null,
            "district": null,
            "town": null,
            "marketAddress": null,
            "marketIntro": null,
            "status": null,
            "processId": 1,
            "loginUserId": null
        }
    ]
}}


17.7 获取工程进度列表
{
url:
"/admin/project/processlist",     
methods:
"GET",
params:{ 
（可选）
 page：
 rows :
 id : 主键
 level : 阶段层级 1-为主层级 2为次层级
 parentId : 父阶段id
},
return:{
{
    "status": {
        "total": 21,
        "timestamp": 1488768269,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 21
    },
    "data": [
        {
            "id": 1,
            "name": "合同阶段",
            "level": 1,
            "parentId": 0
        },
        {
            "id": 2,
            "name": "合同是否签定",
            "level": 2,
            "parentId": 1
        },
        {
            "id": 3,
            "name": "平面阶段",
            "level": 1,
            "parentId": 0
        },
        {
            "id": 4,
            "name": "联系单评分0-100",
            "level": 2,
            "parentId": 3
        },
        {
            "id": 5,
            "name": "电话回访评分",
            "level": 2,
            "parentId": 3
        },
        {
            "id": 6,
            "name": "财务结算",
            "level": 2,
            "parentId": 3
        },
        {
            "id": 7,
            "name": "效果图阶段",
            "level": 1,
            "parentId": 0
        },
        {
            "id": 8,
            "name": "联系单评分0-100",
            "level": 2,
            "parentId": 7
        },
        {
            "id": 9,
            "name": "电话回访评分",
            "level": 2,
            "parentId": 7
        },
        {
            "id": 10,
            "name": "费用结算",
            "level": 2,
            "parentId": 7
        },
        {
            "id": 11,
            "name": "施工图交接阶段",
            "level": 1,
            "parentId": 0
        },
        {
            "id": 12,
            "name": "联系单评分0-100",
            "level": 2,
            "parentId": 11
        },
        {
            "id": 13,
            "name": "电话回访评分",
            "level": 2,
            "parentId": 11
        },
        {
            "id": 14,
            "name": "费用结算",
            "level": 2,
            "parentId": 11
        },
        {
            "id": 15,
            "name": "施工装修阶段",
            "level": 1,
            "parentId": 0
        },
        {
            "id": 16,
            "name": "联系单评分0-100",
            "level": 2,
            "parentId": 15
        },
        {
            "id": 17,
            "name": "电话回访评分",
            "level": 2,
            "parentId": 15
        },
        {
            "id": 18,
            "name": "费用结算",
            "level": 2,
            "parentId": 15
        },
        {
            "id": 19,
            "name": "竣工开业阶段",
            "level": 1,
            "parentId": 0
        },
        {
            "id": 20,
            "name": "电话是否回访",
            "level": 2,
            "parentId": 19
        },
        {
            "id": 21,
            "name": "开业照片上传",
            "level": 2,
            "parentId": 19
        }
    ]
}


}

17.8 根据loginUserId获取工程详情---弃用
{
url:
/admin/project/cusdetail   
methods:
"GET",
params:{ 
// 默认获取当前登录用户
（此接口为获取 提交的合同人的 --我的工程详情，loginUserId为提交的合同人的id）

},
return:{
 {
    "element": {
        "pid": 1,
        "createTime": null,
        "marketId": 1,
        "marketName": "培龙市场",
        "customerId": 1,
        "customerName": "单位名称（客户档案调用）",
        "realContactMoney": 0,
        "contactMoney": 0,
        "firstMoney": 0,
        "firstFinishTime": null,
        "secondMoney": 0,
        "secondFinishTime": null,
        "thirdMoney": 0,
        "thirdFinishTime": null,
        "fourMoney": 0,
        "fourFinishTime": null,
        "fiveMoney": 0,
        "fiveFinishTime": null,
        "remark": "",
        "busTravelType": 0,
        "travelMoney": 0,
        "rebateMoney": 0,
        "extraMoney": 0,
        "signingCompany": 0,
        "province": 1,
        "city": 2,
        "district": 3,
        "town": 4,
        "marketAddress": "",
        "marketIntro": "",
        "status": 0,
        "processId": 1,
        "loginUserId": 1
    },
    "province": {
        "id": 1,
        "name": "北京市",
        "level": 1,
        "parentId": 0
    },
    "district": {
        "id": 3,
        "name": "东城区",
        "level": 3,
        "parentId": 2
    },
    "city": {
        "id": 2,
        "name": "市辖区",
        "level": 2,
        "parentId": 1
    }
}
}

17.9 获取工程具体阶段json
{
url:
/admin/project/prodetail 
methods:
"GET",
params:{ 
projectId :
},
return:{
{
  "status": {
    "total": 6,
    "timestamp": 1489640522,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 6
  },
  "data": [
    {
      "id": 1,
      "status": 0,
      "name": "合同阶段",
      "list": [
        {
          "id": 2,
          "status": 1,
          "name": "合同是否签定"
        }
      ]
    },
    {
      "id": 3,
      "status": 0,
      "name": "平面阶段",
      "list": [
        {
          "id": 4,
          "status": 1,
          "name": "联系单评分0-100"
        },
        {
          "id": 5,
          "status": 0,
          "name": "电话回访评分"
        },
        {
          "id": 6,
          "status": 0,
          "name": "财务结算"
        }
      ]
    },
    {
      "id": 7,
      "status": 0,
      "name": "效果图阶段",
      "list": [
        {
          "id": 8,
          "status": 0,
          "name": "联系单评分0-100"
        },
        {
          "id": 9,
          "status": 0,
          "name": "电话回访评分"
        },
        {
          "id": 10,
          "status": 0,
          "name": "费用结算"
        }
      ]
    },
    {
      "id": 11,
      "status": 0,
      "name": "施工图交接阶段",
      "list": [
        {
          "id": 12,
          "status": 0,
          "name": "联系单评分0-100"
        },
        {
          "id": 13,
          "status": 0,
          "name": "电话回访评分"
        },
        {
          "id": 14,
          "status": 0,
          "name": "费用结算"
        }
      ]
    },
    {
      "id": 15,
      "status": 0,
      "name": "施工装修阶段",
      "list": [
        {
          "id": 16,
          "status": 0,
          "name": "联系单评分0-100"
        },
        {
          "id": 17,
          "status": 0,
          "name": "电话回访评分"
        },
        {
          "id": 18,
          "status": 0,
          "name": "费用结算"
        }
      ]
    },
    {
      "id": 19,
      "status": 0,
      "name": "竣工开业阶段",
      "list": [
        {
          "id": 20,
          "status": 0,
          "name": "电话是否回访"
        },
        {
          "id": 21,
          "status": 0,
          "name": "开业照片上传"
        }
      ]
    }
  ]
}

}
17.10 更改工程具体阶段
{
url:
/admin/project/editprodetail 
methods:
"POST",
params:{ 
type: 1-修改父阶段 ；2-修改子阶段
projectId :
processId :
},
return:{}

17.11 根据customerId获取工程列表
{
url:
/admin/project/getcuslist
methods:
"get",
params:{ 
customerId : 客户id
},
return:{
{
    "status": {
        "total": 1,
        "timestamp": 1489980222,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "element": {
                "pid": 7,
                "createTime": 1489622400,
                "marketId": 0,
                "marketName": "文二农贸市场名称",
                "customerId": 2,
                "customerName": "文二农贸市场",
                "realContactMoney": 19000000,
                "contactMoney": 10000000,
                "firstMoney": 5000000,
                "firstFinishTime": 1489622400,
                "secondMoney": 5000000,
                "secondFinishTime": 1490140800,
                "thirdMoney": 2000000,
                "thirdFinishTime": 1489881600,
                "fourMoney": 0,
                "fourFinishTime": 1489795200,
                "fiveMoney": 0,
                "fiveFinishTime": 1490054400,
                "remark": "",
                "busTravelType": 0,
                "travelMoney": 1000000,
                "rebateMoney": 1000000,
                "extraMoney": 1000000,
                "signingCompany": 0,
                "province": 338,
                "city": 569,
                "district": null,
                "town": 586,
                "marketAddress": "阿斯蒂芬af",
                "marketIntro": "斯塔夺",
                "status": 0,
                "processId": 0,
                "loginUserId": 10,
                "uploadPic": null,
                "uploadDoc": null,
                "processStr": null
            },
            "province": {
                "id": 338,
                "name": "天津市",
                "level": 1,
                "parentId": 0
            },
            "district": null,
            "city": {
                "id": 569,
                "name": "市辖县",
                "level": 2,
                "parentId": 338
            }
        }
    ]
}

}
17.12 获取所有工程市场名称和pid
{
url:
/admin/project/marketname
methods:
"get",
params:{ 
},
return:{
{
  "status": {
    "total": 2,
    "timestamp": 1490320615,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 2
  },
  "data": [
    {
      "pid": 18,
      "marketName": "湘湖农贸市场"
    },
    {
      "pid": 19,
      "marketName": "sadda"
    }
  ]
}

}


18.报销
18.1 提交报销
{
url:
"/admin/reimburse/commit",     
methods:
"POST",
params:{ 
reimburseUserId :报销人id
reimburseUserType : 报销人类型:0-员工档案；1-合作方档案-partner_id
money : 报销金额
marketName : 市场名称（可以不填）
event : 报销相关事件说明
typeId : 报销类型id //从extra表获得
invoiceStatus : 发票状态：0-未提供；1-提供；2-部分提供
remark : 备注
},
return:{


}

18.2 获取报销列表
{
url:
"/admin/reimburse/list",     
methods:
"GET",
params:{ 
 （可选）
 page ： 1
 rows : 30
 rid : 报销记录id
 reimburseUserId : 报销人id
 status : 报销状态：0-申请中；1-申请成功；2-关闭

marketName : 市场名称 add on 0320_2044

},
return:{

{
    "status": {
        "total": 1,
        "timestamp": 1489398421,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "rid": 1,
            "reimburseUserId": 1,
            "reimburseUserType": 1,
            "money": 122343,
            "marketName": "培龙市场02271614",
            "event": "报销相关事件说明",
            "typeId": 1,
            "invoiceStatus": 0,
            "remark": "备注",
            "createTime": null,
            "status": 2,
            "operateNo": 0,
            "updateTime": null,
            "loginUserId": 1,
            "typeName": "市场差旅费",
            "reimburseUserName": "ename1"
        }
    ]
}}
18.3 删除报销记录
{
url:
"/admin/reimburse/delete",     
methods:
"POST",
params:{ 
rid : 主键
},
return:{

}
18.4 修改报销记录
{
url:
"/admin/reimburse/edit",     
methods:
"POST",
params:{ 
（必填）
rid : 主键

reimburseUserId :报销人id
reimburseUserType : 报销人类型:0-员工档案；1-合作方档案-partner_id
money : 报销金额
marketName : 市场名称（可以不填）
event : 报销相关事件说明
typeId : 报销类型id //从extra表获得
invoiceStatus : 发票状态：0-未提供；1-提供；2-部分提供
remark : 备注

status ： 报销状态：0-申请中；1-申请成功；2-关闭；3-标记删除
       //不能设置为3，其他都可以设置
},
return:{

}

18.5 获取报销额外信息
{
url:
"/admin/reimburse/extralist",     
methods:
"GET",
params:{ 
 （可选）
 page ： 1
 rows : 30
 rid : 报销记录id
},
return:{
{
    "status": {
        "total": 10,
        "timestamp": 1487741676,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 10
    },
    "data": [
        {
            "id": 1,
            "typeName": "市场差旅费"
        },
        {
            "id": 2,
            "typeName": "谈业务差旅"
        },
        {
            "id": 3,
            "typeName": "客户接待"
        },
        {
            "id": 4,
            "typeName": "快递图文"
        },
        {
            "id": 5,
            "typeName": "设计院挂号"
        },
        {
            "id": 6,
            "typeName": "物业水电租金"
        },
        {
            "id": 7,
            "typeName": "办公相关"
        },
        {
            "id": 8,
            "typeName": "企业活动"
        },
        {
            "id": 9,
            "typeName": "税费相关"
        },
        {
            "id": 10,
            "typeName": "其他"
        }
    ]
}
}
18.6 修改报销类型
{
url:
"/admin/reimburse/extraedit",     
methods:
"POST",
params:{ 
（必填）
id : 主键
typeName : 类型名称
},
return:{

}
18.7 添加报销类型
{
url:
"/admin/reimburse/extraadd",     
methods:
"POST",
params:{ 
（必填）
typeName : 类型名称
},
return:{

}
18.8 删除报销类型
{
url:
"/admin/reimburse/extradelete",     
methods:
"POST",
params:{ 
（必填）
id : 主键
},
return:{

}



19.收入
19.1 提交收入
{
url:
"/admin/salary/commit",     
methods:
"POST",
params:{ 
（必填）
（如果是会计提交时，多传一个字段：finishTime : 工资审批通过时间）
salaryType : 收入类型id //extra表
designContentType ：设计内容id//extra表
projectId : 工程表id


personCommitTime : 收入提交日期
employeeId : 所提交的员工id//员工表
salaryMoney ： 收入金额
remark ： 备注
salaryOther ： 选择收入类型“其他”时，所传字段
designOther : 选择设计内容“修改”时，必传字段
designModify : 选择设计内容“其他”时，必传字段

},
return:{

}

19.2 获取收入列表
{
url:
"/admin/salary/list",     
methods:
"GET",
params:{ 
 （可选）
 page ： 1
 rows : 30
 sid : 收入主键
 employeeId: 员工id
 projectId : 工程id
 status : 状态：0-未审核；1-审核成功；2-已关闭

startTime : 收入审核通过时间，起始时间 
endTime :  收入审核通过时间，结束时间

marketName : 市场名称 add on 0320_2045
},
return:{
{
  "status": {
    "total": 3,
    "timestamp": 1489399720,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "sid": 1,
      "personCommitTime": 1487644240,
      "employeeId": 1,
      "projectId": 1,
      "salaryType": 12,
      "designContentType": 17,
      "salaryMoney": 1000,
      "remark": "备注",
      "status": 0,
      "createTime": 1487644240,
      "finishTime": 1487644240,
      "operateNo": 1,
      "loginUserId": 1,
      "employeeName": "ename1",
      "projectMarketName": "培龙市场",
      "salaryTypeName": "收入其他备注",
      "designContentTypeName": "给排水设计",
      "username": null
    },
    {
      "sid": 2,
      "personCommitTime": 1487644240,
      "employeeId": 5,
      "projectId": 1,
      "salaryType": 12,
      "designContentType": 17,
      "salaryMoney": 12313,
      "remark": null,
      "status": 1,
      "createTime": null,
      "finishTime": null,
      "operateNo": null,
      "loginUserId": null,
      "employeeName": "ename5",
      "projectMarketName": "培龙市场",
      "salaryTypeName": null,
      "designContentTypeName": "给排水设计",
      "username": null
    },
    {
      "sid": 8,
      "personCommitTime": 1234567890,
      "employeeId": 1,
      "projectId": 1,
      "salaryType": 3,
      "designContentType": 25,
      "salaryMoney": 1200,
      "remark": null,
      "status": 0,
      "createTime": 1487942988,
      "finishTime": null,
      "operateNo": null,
      "loginUserId": 1,
      "employeeName": "ename1",
      "projectMarketName": "培龙市场",
      "salaryTypeName": "出差",
      "designContentTypeName": "设计其他备注",
      "username": null
    }
  ]
}

}

19.3 删除收入记录
{
url:
"/admin/salary/delete",     
methods:
"POST",
params:{ 
 sid ： 收入记录id
},
return:{
}
19.4 获取收入类型和设计内容
{
url:
"/admin/salary/extra",     
methods:
"GET",
params:{ 
（可选）
 tid : 主键id
 typeName : 名称
type : 0-收入类型；1-设计内容
},
return:{
{
  "status": {
    "total": 25,
    "timestamp": 1490260954,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 25
  },
  "data": [
    {
      "tid": 1,
      "typeName": "提成",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 2,
      "typeName": "阶段奖金",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 3,
      "typeName": "出差",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 4,
      "typeName": "业绩奖金",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 5,
      "typeName": "底薪",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 6,
      "typeName": "管理培训推广",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 7,
      "typeName": "管理系统推广",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 8,
      "typeName": "管理教材推广",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 9,
      "typeName": "电商推广",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 10,
      "typeName": "设备推广",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 11,
      "typeName": "招商推广",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 12,
      "typeName": "其他",
      "type": 0,
      "typeMoney": null
    },
    {
      "tid": 13,
      "typeName": "效果图思路设计",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 14,
      "typeName": "效果图思路+建模",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 15,
      "typeName": "建模+渲染",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 16,
      "typeName": "施工图设计",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 17,
      "typeName": "给排水设计",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 18,
      "typeName": "电气设计",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 19,
      "typeName": "整套消防设计",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 20,
      "typeName": "暖通设计",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 21,
      "typeName": "仅建模",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 22,
      "typeName": "仅渲染",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 23,
      "typeName": "效果图思路+建模+渲染",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 24,
      "typeName": "修改",
      "type": 1,
      "typeMoney": null
    },
    {
      "tid": 25,
      "typeName": "其他",
      "type": 1,
      "typeMoney": null
    }
  ]
}


}

19.5 修改工资详情
{
url:
"/admin/salary/edit",     
methods:
"POST",
params:{ 
（必填）
sid :主键id

personCommitTime : 收入提交日期
employeeId : 所提交的员工id//员工表
projectId : 工程表id
salaryType : 收入类型id //extra表
designContentType ：设计内容id//extra表
salaryMoney ： 收入金额
remark ： 备注
salaryOther ： 选择收入类型“其他”时，所传字段
designOther : 选择设计内容“修改”时，必传字段
designModify : 选择设计内容“其他”时，必传字段

status : 只能设置为2,其他会报错
   状态：0-未审核；1-审核成功；2-已关闭；3-标记删除
},
return:{
}
19.6 审核收入通过
{
url:
"/admin/salary/confirm",     
methods:
"POST",
params:{ 
（必填）
sid :主键id
},
return:{
}

19.7 编辑工资类型或设计类型
{
url:
"/admin/salary/extraedit",     
methods:
"POST",
params:{ 
（必填）
tid :主键id
type : 0-工资类型 ；1-设计类型
（可选）
typeName : 类型名称
typeMoney : 对应金额
},
return:{
}

19.8 删除工资类型或设计类型
{
url:
"/admin/salary/extradelete",     
methods:
"POST",
params:{ 
（必填）
tid :主键id
},
return:{
}

19.9 增加工资类型或设计类型
{
url:
"/admin/salary/extraadd",     
methods:
"POST",
params:{ 
（必填）
typeName : 类型名称
type ； 0-工资 1-设计类型
//如果是工资时，此字段必填
typeMoney : 金额
},
return:{
}


20.客户档案
20.1 提交客户档案
{
url:
"/admin/customer/commit",     
methods:
"POST",
params:{ 
companyName ： 单位名称
manager ： 负责人
mobile ： 18010671999
address ： 地址
receivePerson ： 收件人名字
receiveMobile ： 18010671999
remark ： 备注

},
return:{
{
  "status": {
    "timestamp": 1487932215,
    "code": 200,
    "msg": "success"
  }
}

}
20.2 获取客户档案列表
{
url:
"/admin/customer/list",     
methods:
"GET",
params:{ 
（可选）
 cid : 客户档案id
 companyName : 单位名称
 startTime : 开始时间
 endTime : 结束时间
 status : 状态：0-正常；1-冻结; 
},
return:{
{
  "status": {
    "total": 1,
    "timestamp": 1487932407,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "cid": 1,
      "companyName": "单位名称",
      "manager": "负责人",
      "mobile": "18010671999",
      "address": "地址",
      "receivePerson": "收件人名字",
      "receiveMobile": "18010671999",
      "remark": "备注",
      "status": 0,
      "createTime": 1487932215,
      "loginUserId": 1
"username": zhanghao
    "password": mima

    }
  ]
}
}


20.3 删除客户档案记录
{
url:
"/admin/customer/delete",     
methods:
"POST",
params:{ 
（必填）
 cid : 客户档案id
},
return:{
}
20.4 编辑客户档案记录
{
url:
"/admin/customer/edit",     
methods:
"POST",
params:{ 
（必填）
 cid : 客户档案id
 (可填）
companyName ： 单位名称
manager ： 负责人
mobile ： 18010671999
address ： 地址
receivePerson ： 收件人名字
receiveMobile ： 18010671999
remark ： 备注

status : 状态：0-正常；1-冻结; 2-标记删除
      //3-请用删除接口，这里修改会报错

username : 账号
password : 密码

},
return:{
}







21.帖子及评论管理
21.1 发布图面
{
url:
/admin/post/commit   
methods:
"POST",
params:{ 
（必填）
loginUserId : 当前登录用户id
postTitle : //你想发布什么
type: processId 帖子发布的阶段
projectId ： 所属项目工程的id
employeeIds : 找谁解决id，用：隔开
（可选）
content: 你想说什么
uploadImg : 上传图片s
uploadDoc : 上传的文件

openId : 必填
 
},
return:{
{
  "status": {
    "timestamp": 1487932215,
    "code": 200,
    "msg": "success"
  }
}
 
}

21.2 获取帖子列表
{
url:
"/admin/post/list",     
methods:
"POST",
params:{ 
（可选）
projectId : 项目id
 pid : 帖子主键id
nickName : 用户名，模糊搜索
 
//此接口用于 项目提问解答-我的项目（原我的合同）
},
return:{
{
    "status": {
        "total": 1,
        "timestamp": 1489036798,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "element": {
                "pid": 3,
                "loginUserId": 1,
                "nickName": "haha",
                "postTitle": "帖子标题2",
                "type": 3,
                "content": "具体内容",
                "uploadImg": "key1#key2",
                "uploadDoc": "key3#key4",
                "createTime": 1487905895,
                "projectId": 2,
                "status": 0
            },
            "commentCount": 0,
            "userImg": "head1"
        }
    ]
}
}
21.3 获取帖子评论列表
{
url:
"/admin/pcomment/list",     
methods:
"POST",
params:{ 
（必填）
postId : 发布的帖子id
（可选）
loginUserName : 帖子发布人名称
 
},
return:{
{
    "status": {
        "total": 1,
        "timestamp": 1489037343,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "element": {
                "pcid": 19,
                "loginUserId": 5,
                "loginUserName": "郑小小",
                "parentId": 0,
                "parentUserName": "未知用户",
                "imgUrl": null,
                "createTime": 1489031303,
                "postId": 1,
                "content": null
            },
            "userImg": "head1"
        }
    ]
} 
}
21.4 回复帖子评论
{
url:
"/admin/pcomment/reply",     
methods:
"POST",
params:{ 
（必填）
loginUserId : 当前登录人id
postId : 回复的帖子id
content: 回复的内容
（可选）
 parentId : 如果回复某条评论，此为回复评论的id。默认为0。
 imgUrl : 回复带有图片，用#隔开

openId : 如果是客户回复，多传一个这个字段

type : 0-不提醒1-全部员工2-指定员工 
employeeIds : 如果有指定员工，以:号分隔
},
return:{
{
  "status": {
    "timestamp": 1487932215,
    "code": 200,
    "msg": "success"
    }
 }
}

21.5 删除帖子评论以及所有子评论
{
url:
"/admin/pcomment/delete",     
methods:
"POST",
params:{ 
（必填）
 pcid : 帖子评论主键
 //此删除为物理删除
},
return:{
{
  "status": {
    "timestamp": 1487932215,
    "code": 200,
    "msg": "success"
    }
 }
}
21.6 根据工程id获取所有帖子列表
{
url:
"/admin/project/postlist",     
methods:
"GET",
params:{ 
（必填）
 projectId : 工程id
（可选）
 type : 工程阶段
 pid : 帖子id

//此接口可用于 我的项目-查看详情-发帖列表页
},
return:{
{
    "status": {
        "total": 2,
        "timestamp": 1488787949,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "element": {
                "pid": 1,
                "loginUserId": 1,
                "nickName": "haha",
                "postTitle": "标题咯",
                "type": 1,
                "content": "内容",
                "uploadImg": "key1#key2",
                "uploadDoc": "key3#key4",
                "createTime": 1487905893,
                "projectId": 1,
                "status": 0
            },
            "commentCount": 3,
            "userImg": null
        },
        {
            "element": {
                "pid": 2,
                "loginUserId": 1,
                "nickName": "haha",
                "postTitle": "帖子标题2",
                "type": 2,
                "content": "具体内容",
                "uploadImg": "key1#key2",
                "uploadDoc": "key3#key4",
                "createTime": 1487905894,
                "projectId": 1,
                "status": 0
            },
            "commentCount": 0,
            "userImg": null
        }
    ]
}

}

21.7 获取我的解答列表--员工
{
url:
"/admin/post/mylist",     
methods:
"GET",
params:{ 
type : 0- 查找用户相关的最新"评论"帖子列表 --与我相关
 	  1-查找用户相关的最新"@"帖子列表  --我的订单
      2-查找发布用的所有“发布”帖子列表 --我发布的
//此接口用于 我的解答
},
return:{
{
    "status": {
        "total": 2,
        "timestamp": 1489045036,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "pid": 1,
            "loginUserId": 8,
            "nickName": "郑小小_cus",
            "postTitle": "合同签好后，账号什么时候给我们啊",
            "type": 1,
            "content": null,
            "uploadImg": null,
            "uploadDoc": null,
            "createTime": 1483261200,
            "projectId": 1,
            "status": 0,
            "processName": "合同阶段",
            "marketName": "培龙市场",
            "commentCount": 3
        },
        {
            "pid": 2,
            "loginUserId": 8,
            "nickName": "郑小小_cus",
            "postTitle": "平面设计效果图什么时候出",
            "type": 3,
            "content": null,
            "uploadImg": null,
            "uploadDoc": null,
            "createTime": 1483284180,
            "projectId": 1,
            "status": 0,
            "processName": "平面阶段",
            "marketName": "培龙市场",
            "commentCount": 0
        }
    ]
}}

21.8 获取我的提问列表--客户
{
url:
"/admin/post/cuspostlist",     
methods:
"GET",
params:{ 
type : 0- 查找用户相关的最新"评论"帖子列表 --与我相关
 	  1-查找用户相关的最新"发布"帖子列表  --我的发布
//此接口用于 我的解答
},
return:{


22.加盟
22.1 申请加盟
{
url:
/cooperate/publish
methods:
"POST",
params:{ 
（必填）
fullName : 公司全名
province ：
city
district
mainBusiness ： 主要业务id，以:分隔
companyIntro : 公司介绍

 
},
return:{
{
  "status": {
    "timestamp": 1487932215,
    "code": 200,
    "msg": "success"
  }
}
 
}

22.2 获取加盟列表
{
url:
/cooperate/list   
methods:
"get",
params:{ 
（可选）
cid : id
userId : 申请人id
status : 状态：0-未发布；1-已合作；2-合作关闭
fullName : 公司全名，模糊搜索

page :
rows :
 
},
return:{
{
    "status": {
        "total": 2,
        "timestamp": 1488855050,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 1
    },
    "data": [
        {
            "element": {
                "cid": 2,
                "userId": 2,
                "fullName": "fullCompanyname",
                "country": 0,
                "province": 1,
                "city": 2,
                "district": 3,
                "town": null,
                "mainBusiness": "2:5:6",
                "companyIntro": null,
                "createTime": 1487298291,
                "status": 0
            },
            "province": {
                "id": 1,
                "name": "北京市",
                "level": 1,
                "parentId": 0
            },
            "list": [
                "农贸市场管理",
                "农贸市场策划销售定位可研报告",
                "农贸市场投资公司"
            ],
            "district": {
                "id": 3,
                "name": "东城区",
                "level": 3,
                "parentId": 2
            },
            "city": {
                "id": 2,
                "name": "市辖区",
                "level": 2,
                "parentId": 1
            }
        },
        {
            "element": {
                "cid": 2,
                "userId": 2,
                "fullName": "fullCompanyname",
                "country": 0,
                "province": 1,
                "city": 2,
                "district": 3,
                "town": null,
                "mainBusiness": "2:5:6",
                "companyIntro": null,
                "createTime": 1487298291,
                "status": 0
            },
            "province": {
                "id": 1,
                "name": "北京市",
                "level": 1,
                "parentId": 0
            },
            "list": [
                "农贸市场管理",
                "农贸市场策划销售定位可研报告",
                "农贸市场投资公司"
            ],
            "district": {
                "id": 3,
                "name": "东城区",
                "level": 3,
                "parentId": 2
            },
            "city": {
                "id": 2,
                "name": "市辖区",
                "level": 2,
                "parentId": 1
            }
        }
    ]
}


}


22.3 获取主营业务列表
{
url:
/cooperate/buslist   
methods:
"get",
params:{ 
（可选）
id : id
},
return:{
{
    "status": {
        "total": 8,
        "timestamp": 1489127271,
        "curPage": 1,
        "code": 200,
        "msg": "success",
        "pageNum": 8
    },
    "data": [
        {
            "mbid": 1,
            "mainBusinessName": "农贸市场设计"
        },
        {
            "mbid": 2,
            "mainBusinessName": "农贸市场管理"
        },
        {
            "mbid": 3,
            "mainBusinessName": "农贸市场电商"
        },
        {
            "mbid": 4,
            "mainBusinessName": "农贸市场装修"
        },
        {
            "mbid": 5,
            "mainBusinessName": "农贸市场策划销售定位可研报告"
        },
        {
            "mbid": 6,
            "mainBusinessName": "农贸市场投资公司"
        },
        {
            "mbid": 7,
            "mainBusinessName": "农贸市场建设公司"
        },
        {
            "mbid": 8,
            "mainBusinessName": "与LightProject集团战略加盟"
        }
    ]
}

}

22.4 删除加盟公司
{
url:
/cooperate/delete 
methods:
"post",
params:{ 
（可选）
cid : 加盟公司id
 	//标记删除，将状态设置成2
},
return:{
}


23.配置
23.1 获取首页相关配置
{
url:
/config/index
methods:
"get",
params:{ 
 
},
return:{
{
  "status": {
    "timestamp": 1490168405,
    "code": 200,
    "msg": "success"
  },
  "data": {
    "index_ad1": "ad1.png",
    "index_banner_1": "banner.png",
    "newsLit": [
      {
        "typeName": "公司新闻",
        "imgUrl": "news1.jpg",
        "nid": 1,
        "type": 1,
        "newsTitle": "新闻标题1"
      },
      {
        "typeName": "行业资讯",
        "imgUrl": "",
        "nid": 6,
        "type": 2,
        "newsTitle": "农贸市场火了"
      },
      {
        "typeName": "未分类",
        "imgUrl": "news2.jpg",
        "nid": 2,
        "type": 0,
        "newsTitle": "hahha"
      }
    ],
    "goodsList": [
      {
        "imgUrl": "goods1.jpg",
        "goodsId": 1,
        "price": 10000,
        "goodsName": "商品名1"
      },
      {
        "imgUrl": "goods3.jpg",
        "goodsId": 2,
        "price": 20000,
        "goodsName": "商品名2"
      }
    ],
    "index_ad2": "ad2.png",
    "marketList": [
      {
        "imgUrl": "market1.jpg",
        "marketName": "培龙市场",
        "mid": 1
      },
      {
        "imgUrl": "market2.jpg",
        "marketName": "九华山市场",
        "mid": 2
      },
      {
        "imgUrl": "market3.jpg",
        "marketName": "LightProject市场",
        "mid": 3
      },
      {
        "imgUrl": "market4.jpg",
        "marketName": "菜园市场",
        "mid": 4
      }
    ],
    "lastCase": [
      {
        "cid": 1,
        "userId": 1,
        "imgUrl": "market1.jpg",
        "caseName": "黄山农贸集合市场",
        "level": 1,
        "description": "这是一个挺好的案例",
        "createTime": 1489658270,
        "area": "100平米",
        "state": 1
      },
      {
        "cid": 12,
        "userId": 5,
        "imgUrl": "2017/3/16/1489658254300/1#2017/3/16/1489658265100/7",
        "caseName": "好农贸市场",
        "level": 5,
        "description": "11111",
        "createTime": 1489658270,
        "area": "200",
        "state": 1
      },
      {
        "cid": 11,
        "userId": 3,
        "imgUrl": "",
        "caseName": "测试案例2",
        "level": 0,
        "description": "测试啦啦啦",
        "createTime": 1489649808,
        "area": "100",
        "state": 1
      }
    ],
    "settingMap": {
      "mobile_number": "400-660-8888",
      "ICP": "浙ICP备 12039888号",
      "copyright": "Copyright © 2009-2014 LightProject集团",
      "index_ad2_url": "http://www.baidu.com"
    },
    "caseList": [
      {
        "imgUrl": "market3.jpg",
        "level": "装修中",
        "area": "30平米",
        "cid": 4,
        "caseName": "LightProject市场"
      },
      {
        "imgUrl": "market2.jpg",
        "level": "五星级",
        "area": "50平米",
        "cid": 3,
        "caseName": "张小萌农贸市场"
      },
      {
        "imgUrl": "market1.jpg",
        "level": "装修中",
        "area": "100平米",
        "cid": 1,
        "caseName": "黄山农贸集合市场"
      }
    ],
    "picList": [
      {
        "id": 9,
        "name": "index_small_pic_1",
        "value": "pic1.png",
        "incType": "index-small",
        "description": "首页小图"
      },
      {
        "id": 10,
        "name": "index_small_pic_2",
        "value": "pic2.png",
        "incType": "index-small",
        "description": null
      },
      {
        "id": 11,
        "name": "index_small_pic_3",
        "value": "pic3.png",
        "incType": "index-small",
        "description": null
      },
      {
        "id": 12,
        "name": "index_small_pic_4",
        "value": "pic4.png",
        "incType": "index-small",
        "description": null
      },
      {
        "id": 13,
        "name": "index_small_pic_5",
        "value": "pic5.png",
        "incType": "index-small",
        "description": null
      },
      {
        "id": 14,
        "name": "index_small_pic_6",
        "value": "pic6.png",
        "incType": "index-small",
        "description": null
      }
    ]
  }
}
}

23.2 获取招租图片
{
url:
/config/bus
methods:
"get",
params:{ 
 
},
return:{
{
  "status": {
    "timestamp": 1489486519,
    "code": 200,
    "msg": "success"
  },
  "data": [
    {
      "id": 8,
      "name": "bus_pic1",
      "value": "n1.jpg",
      "incType": "bus-pic",
      "description": "招租图片"
    },
    {
      "id": 21,
      "name": "bus_pic2",
      "value": "n1.jpg",
      "incType": "bus-pic",
      "description": null
    },
    {
      "id": 22,
      "name": "bus-pic3",
      "value": "n1.jpg",
      "incType": "bus-pic",
      "description": null
    },
    {
      "id": 23,
      "name": "bus-pic4",
      "value": "n1.jpg",
      "incType": "bus-pic",
      "description": null
    },
    {
      "id": 24,
      "name": "bus-pic5",
      "value": "n1.jpg",
      "incType": "bus-pic",
      "description": null
    }
  ]
}


}

23.3 设置首页相关配置
{
url:
/admin/config/setindex
methods:
"post",
params:{ 
 type : 1-第一张广告图 2-第二张广告图 3-电话号码；4-ICP ;5-copyright;6-后台设置链接的banner 
urlKey : key,和对应的内容
},
return:{
{
  "status": {
    "timestamp": 1488955194,
    "code": 200,
    "msg": "success"
  },
  "data": {
  
  }
}
}

23.4 设置首页小图
{
url:
/admin/config/setsmallpic
methods:
"post",
params:{ 
 num : 1-6，代表第几张小图
urlKey : key
},
return:{
{
  "status": {
    "timestamp": 1488955194,
    "code": 200,
    "msg": "success"
  },
  "data": {
  
  }
}
}

23.5 设置招租图片
{
url:
/admin/config/setbus
methods:
"post",
params:{ 
 num : 1-5，代表第几张招租图片
//1-案例，2-招商，3-设备，4-加盟，5-新闻
urlKey : key
},
return:{
{
  "status": {
    "timestamp": 1488955194,
    "code": 200,
    "msg": "success"
  },
  "data": {
  
  }
}
}

23.6 设置置顶
{
url:
/admin/config/settop
methods:
"post",
params:{ 
 type : 1-4 //1-案例，2-招商，3-设备 4-新闻
 id: 
},
return:{
{
  "status": {
    "timestamp": 1488955194,
    "code": 200,
    "msg": "success"
  },
  "data": {
  
  }
}
}
23.7 设置公司内容
{
url:
/admin/config/setintro
methods:
"post",
params:{ 
 id : 1-4 //1-公司简介，2-公司资质，3-经营范围 4-联系我们

 （可选）
 typeName : 名称
content : 富文本编辑器内容
},
return:{
{
  "status": {
    "timestamp": 1488955194,
    "code": 200,
    "msg": "success"
  },
  "data": {
  
  }
}
}
23.8 获取公司内容
{
url:
/config/introlist
methods:
"get",
params:{ 
 可选
id : 1 - 4
},
return:{
{
  "status": {
    "total": 4,
    "timestamp": 1490867343,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 4
  },
  "data": [
    {
      "id": 1,
      "content": null,
      "typename": "公司简介"
    },
    {
      "id": 2,
      "content": null,
      "typename": "公司资质"
    },
    {
      "id": 3,
      "content": null,
      "typename": "经营范围"
    },
    {
      "id": 4,
      "content": null,
      "typename": "联系我们"
    }
  ]
}

}

23.9 获取图片水印后缀
{
url:
/watermark
methods:
"get",
params:{ 
},
return:{
{
  "status": {
    "timestamp": 1491530324,
    "code": 200,
    "msg": "success"
  },
  "data": "imageView2/3/w/200/h/320/q/75|watermark/2/text/5Yac6LS46K6-6K6h55S15ZWG566h55CG77yad3d3Lmd5Z3lneS5jb20=/font/6buR5L2T/fontsize/4000/fill/I0ZGRkVGRQ==/dissolve/100/gravity/SouthEast/dx/10/dy/10|imageslim"
}
}
23.10 设置图片水印后缀
{
url:
/watermark/edit
methods:
"get",
params:{ 
value : 七牛获取到的图片后缀
},
return:{
{
  "status": {
    "timestamp": 1491530324,
    "code": 200,
    "msg": "success"
  },
  "data": 
}


24.前台评论
24.1 获取前台评论列表
{
url:
/comment/list
methods:
"get",
params:{ 
  id : 评论主键
  type : 评论类型：1-案例；2-市场；3-新闻；4-设备
  typeId : 评论对应的类型id，例如案例id
  userId : 用户id
  
  page :
  rows :
},
return:{
{
  "status": {
    "total": 4,
    "timestamp": 1489132216,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "id": 1,
      "userId": 1,
      "nickName": "TobyHan",
      "imgUrl": "head1.jpg",
      "createTime": 1489131976,
      "typeId": 1,
      "content": "案例评论",
      "type": 1
    },
    {
      "id": 2,
      "userId": 1,
      "nickName": "TobyHan",
      "imgUrl": "head1.jpg",
      "createTime": 1489131976,
      "typeId": 1,
      "content": "市场评论",
      "type": 2
    },
    {
      "id": 3,
      "userId": 1,
      "nickName": "TobyHan",
      "imgUrl": "head1.jpg",
      "createTime": 1489131976,
      "typeId": 1,
      "content": "新闻评论",
      "type": 3
    },
    {
      "id": 4,
      "userId": 1,
      "nickName": "TobyHan",
      "imgUrl": "head1.jpg",
      "createTime": 1489131976,
      "typeId": 1,
      "content": "设备评论",
      "type": 4
    }
  ]
}

}

24.2 发表评论
{
url:
/comment/add
methods:
"post",
params:{ 
  （必填）
type : 类型
typeId : 对应id
userId : 微信用户id
content : 评论内容
imgUrl : 评论图片
},
return:{
}
24.3 删除评论（物理删除）
{
url:
/comment/delete
methods:
"post",
params:{ 
  （必填）
id : 评论id
},
return:{
}

25.统计
25.1 我的收益
{
url:
/admin/total/staffinfo
methods:
"Get",
params:{ 
  （必填）
employeeId : 员工id
startTime : 开始时间
endTime : 结束时间
},
return:{
status : 0-未审核，1-已审核，2-已关闭
job: 1-设计师，2-合同人
当员工时设计师时，totalSalary（全部）和monthSalary（当月） ，取sumSalaryMoney--即工资总和。
当员工是合同人时，取取bonusMoney，分红总和。totalPersonSalary为个人业绩收入总和

{
  "status": {
    "timestamp": 1490606998,
    "code": 200,
    "msg": "success"
  },
  "data": {
    "totalSalary": [
      {
        "status": 0,
        "bonusMoney": 7957,
        "sumSalaryMoney": null
      },
      {
        "status": 1,
        "bonusMoney": 1200,
        "sumSalaryMoney": null
      }
    ],
    "job": 2,
    "monthSalary": [
      {
        "status": 1,
        "bonusMoney": 1200,
        "sumSalaryMoney": null
      },
      {
        "status": 0,
        "bonusMoney": 7957,
        "sumSalaryMoney": null
      }
    ],
    "totalPersonSalary": 60000
  }
}
}

26.备忘录
26.1 添加备忘录记录
{
url:
/admin/memo/add
methods:
"Get",
params:{ 
  （必填）
projectId : 工程id, 如果未选择工程，可以设置为0
processId : 阶段id,1-6代表6个阶段
loginUserId : 登录id
content: 备忘记录内容
},
return:{
{
  "status": {
    "timestamp": 1490025168,
    "code": 200,
    "msg": "success"
  }
}
}
26.2 获取备忘录记录列表
{
url:
/admin/memo/list
methods:
"Get",
params:{ 
  （可选）
id : 记录id
projectId : 工程id, 如果未选择工程，可以设置为0
processId : 阶段id,1-6代表6个阶段
loginUserId : 登录id
},
return:{
{
  "status": {
    "total": 6,
    "timestamp": 1490025348,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "id": 2,
      "projectId": 13,
      "processId": 1,
      "loginUserId": 22,
      "content": "合同阶段--修改首行",
      "createTime": 1490021701
    },
    {
      "id": 3,
      "projectId": 13,
      "processId": 2,
      "loginUserId": 22,
      "content": "平面阶段--",
      "createTime": 1490021711
    },
    {
      "id": 4,
      "projectId": 13,
      "processId": 3,
      "loginUserId": 22,
      "content": "效果图阶段--",
      "createTime": 1490021722
    },
    {
      "id": 5,
      "projectId": 13,
      "processId": 4,
      "loginUserId": 22,
      "content": "施工图阶段--",
      "createTime": 1490021800
    },
    {
      "id": 6,
      "projectId": 13,
      "processId": 5,
      "loginUserId": 22,
      "content": "施工装修阶段--",
      "createTime": 1490021811
    },
    {
      "id": 7,
      "projectId": 13,
      "processId": 6,
      "loginUserId": 22,
      "content": "竣工阶段--添加的结果",
      "createTime": 1490025168
    }
  ]
}

}
26.3 编辑备忘录记录
{
url:
/admin/memo/edit
methods:
"Get",
params:{ 
  （必填）
id:备忘记录id
content: 备忘记录内容
},
return:{
{
  "status": {
    "timestamp": 1490025168,
    "code": 200,
    "msg": "success"
  }
}
}
26.4 删除备忘录记录
{
url:
/admin/memo/delete
methods:
"POST",
params:{ 
  （必填）
id:备忘记录id
},
return:{
{
  "status": {
    "timestamp": 1490025168,
    "code": 200,
    "msg": "success"
  }
}
}
27.搜索
27.1 全部搜索
{
url:
/search
methods:
"POST",
params:{ 
  （必填）
key : 关键词
（可选）
page : 1
rows : 3 默认3条
},
return:{
{
  "status": {
    "timestamp": 1490238379,
    "code": 200,
    "msg": "success"
  },
  "data": {
    "cooperateList": [],
    "goodsList": [],
    "newsList": [
      {
        "contentId": 6,
        "content": "农贸市场火了",
        "type": null
      }
    ],
    "marketList": [
      {
        "contentId": 16,
        "content": "Toby0814发布的市场",
        "type": null
      },
      {
        "contentId": 15,
        "content": "终极大市场",
        "type": null
      },
      {
        "contentId": 14,
        "content": "差农贸市场",
        "type": null
      }
    ],
    "caseList": [
      {
        "contentId": 1,
        "content": "黄山农贸集合市场",
        "type": null
      },
      {
        "contentId": 12,
        "content": "好农贸市场",
        "type": null
      },
      {
        "contentId": 8,
        "content": "有名字的市场",
        "type": null
      }
    ]
  }
}

}
27.2 模块搜索
{
url:
/search/other
methods:
"POST",
params:{ 
  （必填）
key : 关键词
type ； 1-案例 2-招商 3-设备 4-新闻 5-加盟
（可选）
page : 1
rows : 3 默认3条
},
return:{
{
  "status": {
    "total": 6,
    "timestamp": 1490238433,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "contentId": 1,
      "content": "黄山农贸集合市场",
      "type": 1
    },
    {
      "contentId": 12,
      "content": "好农贸市场",
      "type": 1
    },
    {
      "contentId": 8,
      "content": "有名字的市场",
      "type": 1
    },
    {
      "contentId": 6,
      "content": "没有名字的市场",
      "type": 1
    },
    {
      "contentId": 4,
      "content": "LightProject市场",
      "type": 1
    },
    {
      "contentId": 3,
      "content": "张小萌农贸市场",
      "type": 1
    }
  ]
}

}

27.3 获取热词
{
url:
/search
methods:
"POST",
params:{ 
page : 1 默认为1
rows : 8 默认8条
},
return:{
{
  "status": {
    "total": 2,
    "timestamp": 1490250378,
    "curPage": 1,
    "code": 200,
    "msg": "success",
    "pageNum": 1
  },
  "data": [
    {
      "id": 1,
      "contentid": null,
      "content": "daddx",
      "type": 23,
      "createTime": null,
      "heat": 535
    },
    {
      "id": 5,
      "contentid": null,
      "content": "dsag",
      "type": 1,
      "createTime": null,
      "heat": 435
    }
  ]
}

}

28.搜索
28.1 获取Jsapi_ticket配置
{
url:
/sign
methods:
"POST",
params:{ 
  （必填）
url : 当前要应用的url
},
return:{
{
  "status": {
    "timestamp": 1490350947,
    "code": 200,
    "msg": "success"
  },
  "data": {
    "timestamp": "1490350929",
    "appid": "wx6749be7c611a889c",
    "nonceStr": "80279f86-ef50-4ec0-a50f-5500ea1206c2",
    "jsapi_ticket": "sM4AOVdWfPE4DxkXGEs8VCm-9uxTY9DmGfUdOO1GMseAkj0s0gRMOfW-j8yhUi254vM8UtrAch9xj9jSZxSlmg",
    "signature": "90f662bd29385ae8d0e76c70b492c195cbc1da75",
    "url": "http://wytechhome.com/randomer-index/index.html"
  }
}

}
