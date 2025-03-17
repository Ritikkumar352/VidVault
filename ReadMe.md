# Deploy-branch test
# VidVault 

- By default in Spring boot max file size is 1MB and res size is 10 MB
- Increased it to 50 MB in this project... tested upto approx 100MB 
- eraser.io -> steps and diagram https://app.eraser.io/workspace/WBak7oEnw9g7pNnUSWc3?origin=share

- crrently it's uploading all types of file, if neededd try to change to video only
  - can add a check for contentType avl in blob obj

# UPLOAD   
    * for more explanation open link -> 
- https://app.eraser.io/workspace/WBak7oEnw9g7pNnUSWc3?origin=share

- create a storage instance

```java
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;

BlobId blobId = BlobId.of(bucketname, fileNamwe);
BlobInfo blobInfo=BlobInfo.newBuilder(blobId).setContentType(file.getContentTpe().build());
Blob blob=storage.create(blobInfo,file.getBytes());
```
- This blob obj has many details
  - blob.getMediaLink()  ->  public URl
  - blob.getSize()  -> in bytess
  - blob.getName()  ->

- for publi url use --> 
```String downloadUrl = "https://storage.googleapis.com/" + bucketName + "/" + blob.getName()"; ```

# DELETE 
```java
    storage.delete(blobId); 
```
- ?? -> blob.getBlobId  ?




