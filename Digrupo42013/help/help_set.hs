<helpset version="1.0">
	<title>Documentación de la aplicación Tienda</title>
	<!-- Archivo de mapeo -->
	<maps>
		<homeID>portada</homeID>
		<mapref location="map_file.jhm"/>
	</maps>
	<!-- Tabla de contenidos -->
	<view>
		<name>Tabla de contenidos</name>
		<label>Tabla de contenidos</label>
		<type>javax.help.TOCView</type>
		<data>toc.xml</data>
	</view>
	<!-- Índice -->
	<view>
		<name>Indice</name>
		<label>Indice</label>
		<type>javax.help.IndexView</type>
		<data>indice.xml</data>
	</view>
	<!-- Búsqueda -->
	<view>
		<name>Buscar</name>
		<label>Buscar</label>
		<type>javax.help.SearchView</type>
		<data engine="com.sun.java.help.search.DefaultSearchEngine">
			JavaHelpSearch
		</data>
	</view>
</helpset>