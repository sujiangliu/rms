//package com.jack.rms.util;
//
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.ObjectMetadata;
//import com.aliyun.oss.model.PutObjectResult;
//
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
///**
// * 该示例代码展示了如果在OSS中创建和删除一个Bucket，以及如何上传和下载一个文件。
// * 
// * 该示例代码的执行过程是：
// * 1. 创建一个Bucket（如果已经存在，则忽略错误码）；
// * 2. 上传一个文件到OSS；
// * 3. 下载这个文件到本地；
// * 4. 清理测试资源：删除Bucket及其中的所有Objects。
// * 
// * 尝试运行这段示例代码时需要注意：
// * 1. 为了展示在删除Bucket时除了需要删除其中的Objects,
// *    示例代码最后为删除掉指定的Bucket，因为不要使用您的已经有资源的Bucket进行测试！
// * 2. 请使用您的API授权密钥填充ACCESS_ID和ACCESS_KEY常量；
// * 3. 需要准确上传用的测试文件，并修改常量uploadFilePath为测试文件的路径；
// *    修改常量downloadFilePath为下载文件的路径。
// * 4. 该程序仅为示例代码，仅供参考，并不能保证足够健壮。
// *
// */
//public class OSSObjectUtil {
//
//    public static final String DOMAIN = "http://static2.8dol.com/";
//    private static final String ACCESS_ID = "dttWknoWt3hM1CcS";
//    private static final String ACCESS_KEY = "zGxNntp9aBM8fdXDkaCJD4Zba7Kthh";
//    private  static final String BUCKET_NAME = "8dol-static-img";
//
//    public static void putObject( String key,InputStream content,long length) throws FileNotFoundException {
//
//        // 初始化OSSClient
//        OSSClient client = new OSSClient(ACCESS_ID, ACCESS_KEY);
//
//        // 创建上传Object的Metadata
//        ObjectMetadata meta = new ObjectMetadata();
//        meta.setContentType("image/jpeg");
//        // 必须设置ContentLength
//        meta.setContentLength(length);
//
//        // 上传Object.
//        PutObjectResult result = client.putObject(BUCKET_NAME, key, content, meta);
//
//        // 打印ETag
//        System.out.println(result.getETag());
//    }
//
//
//}
