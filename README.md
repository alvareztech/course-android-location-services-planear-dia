# Planear Día

Aplicación de ejemplo de uso de Google Places y Google Maps.

> Este proyecto es parte del [Curso de servicios de localización en Android](https://alvarez.tech/courses/android-localizacion/)

> Place Picker esta deprecado, se necesita actualizar este proyecto a `'com.google.android.libraries.places:places:2.2.0'` para que funcione correctamente.

## Paso 1

Abrir la pantalla de selección de lugares de Google Places.

```java
PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
try {
    startActivityForResult(builder.build(this), 888);
} catch (Exception e) {

}
```

## Paso 2

Obtener la respuesta luego de la selección.

```java
Place place = PlacePicker.getPlace(this, data);

String nombre = place.getName().toString();
String direccion = place.getAddress().toString();
double latitud = place.getLatLng().latitude;
double longitud = place.getLatLng().longitude;
String telefono = place.getPhoneNumber() != null ? place.getPhoneNumber().toString() : "No tiene teléfono";
String website = place.getWebsiteUri() != null ? place.getWebsiteUri().getAuthority() : "No tiene website";

Database.guardarUbicacion(this, nombre, direccion, latitud, longitud, telefono, website);
```



## Paso 3

Mostrar los lugares en forma de marcador con una línea entre ellos.

```java
PolylineOptions polylineOptions = new PolylineOptions()
                .color(ContextCompat.getColor(this, R.color.colorPrimary))
                .width(8);

for (int i = 0; i < lugares.size(); i++) {
    Lugar l = lugares.get(i);

    LatLng punto = new LatLng(l.getLatitud(), l.getLongitud());

    MarkerOptions markerOptions = new MarkerOptions()
        .position(punto)
        .title(l.getNombre())
        .snippet(l.getDireccion());

    mMap.addMarker(markerOptions);

    polylineOptions.add(punto);
}
mMap.addPolyline(polylineOptions);


```

## Paso 4

Hacer que la cámara centree los todos puntos en la pantalla.

Antes del `for`

```java
LatLngBounds.Builder builder = new LatLngBounds.Builder();
```

Dentro del `for`

```java
builder.include(punto);
```

Al final y afuera del `for`

```java
LatLngBounds bounds = builder.build();
mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
```