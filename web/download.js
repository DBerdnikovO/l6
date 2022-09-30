function Download({ url, filename }) {
    const [creatingURI, setCreatingURI] = useState(false);

    const download = () => {
        setCreatingURI(true);

        imageDataURI
            .encodeFromURL(url)
            .then(uri => {
                setCreatingURI(false);

                const link = document.createElement("a");
                link.href = uri;
                link.download = `${filename}.gif`;
                document.body.appendChild(link);
                link.click();
            })
            .catch(() => {
                setCreatingURI(false);
            });
    };

}