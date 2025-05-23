# librairie utiles
import os
from . import annexe anx
#import annexe as anx
from PIL import Image
from PIL.ExifTags import TAGS
import numpy as np
import exifread
import cv2

#variable utiles notamment pour tri_date_mois(), decalage_utc()

## fonction pour les tris

# Récupère les métadonnées EXIF d'une image

# Tri par année à partir de la date EXIF
def Annees(image_filename):
    date = anx.metadonnees(image_filename).get('DateTime')
    if date:  #test si données existe dans EXIF
        return date.split(":")[0]
    return "Sans date"

#Tri par nom_appareil à partir des données EXIF
def Appareil(nom):
    image = Image.open(nom)
    mar = anx.metadonnees(image).get('Make')
    mol = anx.metadonnees(image).get('Model')
    if mar:     #test si données existe dans EXIF
        if mol:  #test si données existe dans EXIF
            return (mar+ "_"+ mol)
        return mar
    return "Sans modele"

def Mois (nom):
    image = Image.open(nom)
    date = anx.metadonnees(image).get('DateTime')
    if date:
        return anx.mois[date.split(":")[1]+1]
    return "Sans date"

def Jour (nom):
    image = Image.open(nom)
    date = anx.metadonnees(image).get('DateTime')
    if date:
        return date.split(":")[2]
    return "Sans date"

# Tri par heure à partir de la date EXIF
def Heure (nom):
    image = Image.open(nom)
    date = anx.metadonnees(image).get('DateTime')
    if date:
        return date.split(" ")[1]

    return "Sans date"

# Tri par distinction jour/nuit, il regarde l'heure (jour compris entre 7h et 20h) à partir de la date EXIF
def Jour_nuit (nom):
    image = Image.open(nom)
    date = anx.metadonnees(image).get('DateTime')
    if date:
        heure= int(date.split(" ")[1].split(":")[0])
        if heure>20 or heure<7 :
            return("nuit")
        return("jour")
    return "Sans date"



# Tri par couleur (R=0, B=1, V=2) somme tous les pixel et fais la moyenne renvoit toute ces couleurs: rouge vert bleu blanc jaune noir cyan magenta
def Couleur(nom):
    image = Image.open(nom)
    a = np.sum(np.array(image), axis=(0, 1))  # Somme par canal R, G, B
    a=[int(i/(len(np.array(image))*len(np.array(image)[0]))) for i in a] #moyenne
    if a[0]>122: #les différents test successif pour renvoyer la bonne couleur
        if a[1]>122:
            if a[2]>122:
                return("blanc")
            return("jaune")
        if a[2]>122:
            return ("magenta")
        return("rouge")
    if a[1]>122:
        if a[2]>122:
            return("cyan")
        return("vert")
    if a[2]>122:
        return("bleu")
    return("noir")

# Tri par localisation à partir de la date utc: compare, l'heure local et utc pour definir le fuseau horaire corespondant, prend uniquement les décalages entier ignore les demis comme pour l'inde.
# le dictionnaire définit dans les variable fait le lien entre le fuseau horaire et la localisation
def Localisation(nom):
    image = Image.open(nom)
    date = anx.metadonnees(image).get('DateTime')
    if date:
        utc=anx.get_gps_info(nom)
        if utc:
            heure= int(date.split(" ")[1].split(":")[0]) #comme donnees en "str", utilasation de split pour récuperer le nombre correspondant à l'heure il faudrait s'assuré que ces toujours la meme typologie
            heure_utc=int(utc.split("[")[1].split(",")[0])
            decalage = int(heure -heure_utc)
            if -13 < decalage < 15 :
                    return anx.fuseau_horaire.get(decalage)# utilisation du dictionnaire
    return "Sans date"

def Presence_visage(fil):
    image=Image.open(fil)
    # on convertit l'image en noir et blanc
    # l'algorithme que nous allons utilisé a besoin de ce pretraitement
    image_gray = cv2.cvtColor(np.array(image), cv2.COLOR_BGR2GRAY)
    #https://github.com/opencv/opencv/tree/master/data/haarcascades
    # on charge notre modèle
    demo_directory = os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd())))
    base_path = os.path.join("src","main", "python", "module_de_tri")
    cascade_file = os.path.join(demo_directory,base_path, "haarcascade_frontalface_alt.xml")
    face_cascade = cv2.CascadeClassifier(cascade_file)
    # On cherche tous les visages disponibles dans l'image
    faces = face_cascade.detectMultiScale(image_gray, 1.05, 5,minSize=(50, 50))
    # on écrit dans la console le nombre de visages que  l'algorithme a détecté
    if len(faces)>0:
        return "avec"
    return "sans"

print(Presence_visage("C:\\Users\\Vincent\\IdeaProjects\\demo\\WorkingDirectory\\AlbumExemple\\images\\blanc.png"))
# ## fonction annexe (pas utile si tu veux uniquement les etiquettes pour une photo)

# def tri_total2(i,image):
#     if i == "tri_couleur" :
#         return(tri_couleur(image))
#     if i == "tri_date":
#         return(tri_date(image))
#     return"erreur"

# def tri_total(i,image,dictionnaire,j):
#     if i == "tri_couleur" :
#         a = tri_couleur(image)
#         dictionnaire.get(i).setdefault(a,[])
#         dictionnaire.get(i).get(a).append(j)
#     if i == "tri_date":
#         a = tri_date(image,0)
#         dictionnaire.get(i).setdefault(a,{})
#         dictionnaire.get(i).get(a).setdefault(tri_date(image,1),[])
#         dictionnaire.get(i).get(a).get(tri_date(image, 1)).append(j)
#     return"erreur"

# # Fonction pour lister les fichiers dans un dossier
# # def parcours_dossier(dossier):
# #         return os.listdir(dossier)

# ## Fonction principale

# def main(dossier):
#     Liste_photo= parcours_dossier(dossier)
#     dictionnaire={}
#     for i in Liste:
#         dictionnaire.setdefault(i,{})
#         for j in Liste_photo:
#             image = Image.open(f"./{dossier}/{j}")
#             tri_total(i,image,dictionnaire,j)
#     return(dictionnaire)

# dossier_test = "photo"


# def retri(Liste, bibli):
#     resultats = {}
#     for i in Liste:
#         resultats.setdefault(i, {})
#         for id in bibli:
#             if not isinstance(bibli.get(id).get(i), list):
#                 resultats[i].setdefault(bibli[id][i], []).append(id)
#             else:
#                 resultats[i].setdefault(bibli[id][i][0], []).append(id)
#     return resultats

# if __name__ == "__main__":
#     main()


# dossier = sys.argv[1]


