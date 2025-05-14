from __future__ import print_function
from __future__ import division
import cv2
import os
import numpy as np

def tri_presence_visage(image):
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
    if len(faces)>0:
        return "avec"
    return "sans"

def histogramme(chemin_image):
    image = cv.imread(chemin_image) #cv.samples.findFile()
    if image is None:
        print("Erreur : l'image n'a pas été chargée correctement.")

    bgr_planes = cv2.split(image)
    histSize = 256
    histRange = (0, 256) # the upper boundary is exclusive
    accumulatee = False
    b_hist = cv2.calcHist(bgr_planes, [0], None, [histSize], histRange, accumulate=accumulatee)
    g_hist = cv2.calcHist(bgr_planes, [1], None, [histSize], histRange, accumulate=accumulatee)
    r_hist = cv2.calcHist(bgr_planes, [2], None, [histSize], histRange, accumulate=accumulatee)

    hist_h = 400

    cv2.normalize (b_hist, b_hist, alpha=0, beta=hist_h, norm_type=cv.NORM_MINMAX)
    cv2.normalize (g_hist, g_hist, alpha=0, beta=hist_h, norm_type=cv.NORM_MINMAX)
    cv2.normalize (r_hist, r_hist, alpha=0, beta=hist_h, norm_type=cv.NORM_MINMAX)
    return(b_hist,g_hist,r_hist)