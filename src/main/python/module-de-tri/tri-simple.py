
from PIL import Image
from PIL.ExifTags import TAGS
import numpy as np
import exifread


fuseau_horaire={
    -12: "Etc/GMT+12",
    -11: "Pacific: Pago_Pago Niue Midway",
    -10: "Pacific: Honolulu TahitiRarotonga",
    -9: "America: Anchorage Juneau Gambier",
    -8: "America: Los_Angeles Vancouver Tijuana",
    -7: "America: Denver Phoenix Chihuahua",
    -6: "America: Chicago Guatemala Belize",
    -5: "America: New_York Toronto Bogota",
    -4: "America: Halifax Caracas Santiago",
    -3: "America :Argentina Sao_Paulo Montevideo",
    -2: "America: Noronha Atlantic: South_Georgia",
    -1: "Atlantic: Azores Cape_Verde",
    0: "Europe: London Africa: Abidjan Atlantic: Reykjavik",
    1: "Europe: Paris Berlin Africa: Lagos",
    2: "Europe: Athens Helsinki Africa: Cairo",
    3: "Europe: Moscow Africa: Nairobi Asia: Riyadh",
    4: "Asia: Dubai Baku Europe: Samara",
    5: "Asia: Karachi Tashkent",
    6: "Asia: Dhaka Almaty",
    7: "Asia: Bangkok Jakarta Ho_Chi_Minh",
    8: "Asia: Shanghai Singapore Australia: Perth",
    9: "Asia: Tokyo Seoul Yakutsk",
    10: "Australia: Sydney Pacific Port_Moresby Asia Vladivostok",
    11: "Pacific: Guadalcanal Asia Srednekolymsk",
    12: "Pacific: Auckland Fiji Asia Kamchatka",
    13: "Pacific: Tongatapu Apia",
    14: "Pacific: Kiritimati"
}
## fonction pour le tri par date

# Récupère les métadonnées EXIF d'une image
def metadonnees(my_image):
    L = {}
    try:
        img_exif_data = my_image.getexif()
        for id in img_exif_data:
            tag_name = TAGS.get(id, id)
            data = img_exif_data.get(id)
            if isinstance(data, bytes):
                data = data.decode()
            L[tag_name] = data
    except Exception as e:
        print(f"Erreur lors de l'ouverture de l'image {my_image} : {e}")
    return L

# Tri par année à partir de la date EXIF
def tri_date_année(image_filename):
    date = metadonnees(image_filename).get('DateTime')
    if date:
        return date.split(":")[0]
    return "Sans date"

def tri_date_mois (nom):
    image = Image.open(nom)
    date = metadonnees(image).get('DateTime')
    if date:
        return date.split(":")[1]
    return "Sans date"

def tri_heure (nom):
    image = Image.open(nom)
    date = metadonnees(image).get('DateTime')
    if date:
        return date.split(" ")[1]

    return "Sans date"

def tri_jour_nuit (nom):
    image = Image.open(nom)
    date = metadonnees(image).get('DateTime')
    if date:
        heure= int(date.split(" ")[1].split(":")[0])
        if heure>20 or heure<7 :
            return("nuit")
        return("jour")
    return "Sans date"



# Tri par couleur (R=0, B=1, V=2)
def tri_couleur(nom):
    image = Image.open(nom)
    a = np.sum(np.array(image), axis=(0, 1))  # Somme par canal R, G, B
    a=[int(i/(len(np.array(image))*len(np.array(image)[0]))) for i in a]
    if a[0]>122:
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

def decalage_utc(nom):
    image = Image.open(nom)
    date = metadonnees(image).get('DateTime')
    if date:
        utc=get_gps_info(nom)
        if utc:
            heure= int(date.split(" ")[1].split(":")[0])
            heure_utc=int(utc.split("[")[1].split(",")[0])
            decalage = int(heure -heure_utc)
            if -13 < decalage < 15 :
                    return fuseau_horaire.get(decalage)
    return "Sans date"

# ## fonction pour le tri par localisation (à finir)
#attention problème avec le chemin de l'image
# # Récupère les informations GPS
def get_gps_info(image_filename):
    with open(image_filename, 'rb') as f:
         tags = exifread.process_file(f)
    gps_info = {tag: tags[tag] for tag in tags.keys() if tag.startswith('GPS')}
    return str(gps_info.get('GPS GPSTimeStamp'))


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


