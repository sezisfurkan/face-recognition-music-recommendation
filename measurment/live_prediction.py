import numpy as np
import cv2
import tensorflow as tf

modelpath = 'C:\\Users\\batuh\\Desktop\\duygumodel.h5'
emotion_dict = {'angry': 0, 'disgusted': 1, 'fearful': 2, 'happy': 3, 'neutral': 4, 'sad': 5, 'surprised': 6}
num_classes = len(emotion_dict)
model = tf.keras.models.load_model(modelpath, compile=False)
faceDetect = cv2.CascadeClassifier('C:\\Users\\batuh\\Desktop\\haarcascade_frontalface_default.xml')
video=cv2.VideoCapture(0)

while True:
    ret,frame=video.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    faces= faceDetect.detectMultiScale(gray, 1.3, 3)
    for x,y,w,h in faces:
        sub_face_img=gray[y:y+h, x:x+w]
        resized=cv2.resize(sub_face_img,(128,128))
        normalize=resized/255.0
        reshaped=np.reshape(normalize, (-1, 128, 128, 1))
        result=model.predict(reshaped)
        label=np.argmax(result, axis=1)[0]
        print(label)
        cv2.rectangle(frame, (x,y), (x+w, y+h), (0,0,255), 1)
        cv2.rectangle(frame,(x,y),(x+w,y+h),(50,50,255),2)
        cv2.rectangle(frame,(x,y-40),(x+w,y),(50,50,255),-1)
        emotion_label = list(emotion_dict.keys())[list(emotion_dict.values()).index(label)]
        cv2.putText(frame, emotion_label, (x, y-10),cv2.FONT_HERSHEY_SIMPLEX,0.8,(255,255,255),2)
       
        
    cv2.imshow("Frame",frame)
    k=cv2.waitKey(1)
    if k==ord('q'):
        break

video.release()
cv2.destroyAllWindows()
