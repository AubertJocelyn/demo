import fonctionTri as fT
import os
demo_directory = os.path.dirname(os.path.dirname(os.path.dirname(os.getcwd())))
print(demo_directory )
chemin_image = os.path.join(demo_directory,"WorkingDirectory","AlbumExemple","images")
blanc=os.path.join(chemin_image,"blanc.png")
cyan=os.path.join(chemin_image,"cyan.png")
rouge=os.path.join(chemin_image,"rouge.png")
vert=os.path.join(chemin_image,"vert.png")
meta=os.path.join(chemin_image,"meta.jpg")
imageSh=os.path.join(chemin_image,"image sh.jpg")
if fT.Couleur(blanc) =="blanc":
    if fT.Couleur(cyan) =="cyan":
        if fT.Couleur(rouge) =="rouge":
            if fT.Couleur(vert) =="vert":
                print("test_couleur bon")

if fT.Annees(blanc) == "Sans date":
    if fT.Annees(meta) == "2025":
        print("test_annees bon")

if fT.Localisation(blanc) == "Sans date":
    if fT.Localisation(meta) == "Europe:_Paris_Berlin_Africa:_Lagos":
        print("test_localisation bon")

if fT.Mois(blanc)== "Sans date":
    if fT.Mois(meta) == "avril":
        print("test_mois bon")

if fT.Presence_visage(blanc)=="sans":
    if fT.Presence_visage(imageSh)=="avec":
        print("test_visage bon")

if fT.Appareil(blanc)== "Sans modele":
    if fT.Appareil(meta)=="Xiaomi_Redmi Note 5":
        print("test_Appareils bon")

if fT.Jour(blanc) == "Sans date":
    if fT.Jour(meta) == "14":
        print ("test_jour bon")

if fT.Jour_nuit(blanc) == "Sans date":
    if fT.Jour_nuit(meta) == "jour":
        print("test_jour_nuit bon")

if fT.Heure(blanc) == "Sans date":
    if fT.Heure(meta) == "08:07:52":
        print("test_heure bon")
