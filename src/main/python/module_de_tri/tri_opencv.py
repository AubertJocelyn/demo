import cv2
import os

def tri_visage(image):

    image = cv2.imread(image)


# on convertit l'image en noir et blanc
# l'algorithme que nous allons utilisé a besoin de ce pretraitement
    image_gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)


#https://github.com/opencv/opencv/tree/master/data/haarcascades

# on charge notre modèle
    face_cascade = cv2.CascadeClassifier("haarcascade_frontalface_alt.xml")


# On cherche tous les visages disponibles dans l'image
    faces = face_cascade.detectMultiScale(image_gray, 1.05, 5,minSize=(50, 50))
# on écrit dans la console le nombre de visages que  l'algorithme a détecté
    print( len(faces))

    return( len(faces)>0)
