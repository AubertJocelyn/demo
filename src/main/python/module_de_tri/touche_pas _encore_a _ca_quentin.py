from __future__ import print_function
from __future__ import division
import cv2
import os
import numpy as np
import face_recognition
import json



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



def tri_nombre_visage(image_fil):
    image = face_recognition.load_image_file(image_fil)
    face_locations = face_recognition.face_locations(image)
    return(len(face_locations))

#def tri_visage(liste_encodage,image_fil):
# Charger l'image de référence et encoder le visage
#image_reference = face_recognition.load_image_file("2pers.jpg")
#b = face_recognition.face_encodings(image_reference)
#a=face_recognition.load_image_file("perso1.jpg")
#b=face_recognition.load_image_file("vincent.jpg")

image_fil = "4pers.jpg"
#fonction
def tri_personnes(image_fil):  #tri les photos par reconnaissances de visage
    L=[]                                            #variable pour récuperer les visages reconnue (sortie du programme)
    d=[]                                            #variable pour récupérer les visage pas reconnue pour les ajouté au visage reconnue
    with open("liste_encodage.csv","rb") as f:      #on récupère le fichier contenant les visage déja reconnue, dans la variable liste_encodage
        liste_encodage = np.load(f)
    image_test = face_recognition.load_image_file(image_fil) #on récupére puis enregistre les différentes personnes de la nouvelle photo à trier dans la variable encodages_test
    encodages_test = face_recognition.face_encodings(image_test)
    for i in range (len(encodages_test)):           #on parcours les différents visage de la photo à trier
        for j in range (len(liste_encodage)):       #on parcours les différents visages déja reconnue
            correspondance = face_recognition.compare_faces([liste_encodage[j]], encodages_test[i]) #on teste la reconaissance entre les deux visages
            if correspondance[0] ==True:            #si il y a reconnaissance au rajoute, le visage reconnue à L (le numéro)
                L.append(j)
                break                               #on sort si il y a eu une reconnaissance pour passer à la prochaine image directement
            if j ==len(liste_encodage) -1:          #si il y a eu aucun reconnaissance on rajoute, la photo au photo reconnue pour la prochaine itération
                d.append(encodages_test[i])
                L.append(len(liste_encodage)+len(d)-1) #le visage et reconnue donc comme lui meme
    if len(liste_encodage) == 0:                    # pour le cas particulier si la liste liste_encodage est vide on rajoute tous les visage dans le fichier visage reconnue et on reconnais les visages comme eux meme
        L=[i for i in range (len(encodages_test))]
        with open("liste_encodage.csv","wb") as f:  #on enregistre le nouveau fichier des vissages reconnus
            np.save(f,encodages_test)
    if len(d) != 0:                                 # si il y a des nouveau visage à rajouté, on les rajoute à la précedante liste de vissage reconnue
        liste_encode2= np.concatenate([liste_encodage,d],axis=0)
        with open("liste_encodage.csv","wb") as f:  #on enregistre le nouveau fichier des vissages reconnus
            np.save(f,liste_encode2)
    return(L)                                       #on retourne la liste des visages reconns dans la photo à trier



def creation_fichier():
    with open("liste_encodage.csv","wb") as f:
        np.save(f,[])
