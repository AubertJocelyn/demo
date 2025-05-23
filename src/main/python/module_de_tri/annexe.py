import exifread
from PIL import Image
from PIL.ExifTags import TAGS

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

def get_gps_info(image_filename):
    with open(image_filename, 'rb') as f:
        tags = exifread.process_file(f)
    gps_info = {tag: tags[tag] for tag in tags.keys() if tag.startswith('GPS')}
    return str(gps_info.get('GPS GPSTimeStamp'))

mois=["janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre"]
fuseau_horaire={
    -12: "Etc/GMT+12",
    -11: "Pacific:_Pago_Pago_Niue Midway",
    -10: "Pacific:_Honolulu_TahitiRarotonga",
    -9: "America:_Anchorage_Juneau_Gambier",
    -8: "America:_Los_Angeles_Vancouver_Tijuana",
    -7: "America:_Denver_Phoenix_Chihuahua",
    -6: "America:_Chicago_Guatemala_Belize",
    -5: "America:_New_York_Toronto_Bogota",
    -4: "America:_Halifax_Caracas_Santiago",
    -3: "America: Argentina Sao_Paulo Montevideo",
    -2: "America:_Noronha_Atlantic:_South_Georgia",
    -1: "Atlantic:_Azores_Cape_Verde",
    0: "Europe:_London_Africa:_Abidjan_Atlantic:_Reykjavik",
    1: "Europe:_Paris_Berlin_Africa:_Lagos",
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